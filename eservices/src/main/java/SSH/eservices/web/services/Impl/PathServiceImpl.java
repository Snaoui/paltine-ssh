package SSH.eservices.web.services.Impl;

import SSH.eservices.model.Path;
import SSH.eservices.repository.PathRepository;
import SSH.eservices.repository.UserRepository;
import SSH.eservices.web.dto.PathTO;
import SSH.eservices.web.services.Itf.IPathService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pathService")
public class PathServiceImpl implements IPathService {

	@Autowired
	PathRepository pathRepository;

	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Path get(int id) throws Exception {
		if(!exists(id)){
			throw  new  Exception("Path with id ["+id+"] doesn't exists !");
		}
		return pathRepository.findOne(id);

	}

	@Override
	public List<Path> getAll() {
		List<Path> allPathsList = new ArrayList<>();
		pathRepository.findAll().forEach(allPathsList::add);
		return allPathsList;
	}

	@Override
	public Path create(PathTO pathto) throws Exception {
		Path newPath = pathto.getPath();
		if (userRepository.findByEmail(pathto.getUserEmail()) == null) {
			throw new Exception("Can not create path associate to user [" + pathto.getUserEmail() + "] Cause: user doesn't exist");
		}
		return createPath(newPath);
	}

	@Override
	public Path edit(PathTO pathTO) throws Exception {
		if(pathTO==null || pathTO.getPath()==null){
			throw new  IllegalArgumentException
					("Can not edit null path !");
		}
		if(!exists(pathTO.getPath().getId())){
		  throw new Exception("Can not edit path with id ["+pathTO.getPath().getId()+"] cause: path doesn't exists");
		}
		return pathTO.getPath();
	}

	@Override
	public boolean delete(int id) throws Exception {
		if(!exists(id)){
			throw new Exception("Path with id ["+id+"] doesn't exists !");
		}
		Path path = getPath(id);
		return delete(path);
	}

	public Path getPath(int id) throws Exception {
		if(!exists(id)){
			throw new Exception("Path with id ["+id+"] doesn't exists !");
		}
		return pathRepository.findOne(id);
	}


	@Override
	public void save(Path path) throws Exception {
		createPath( path );
	}

	/**
	 * verify if  path associate with @param id exist
	 *
	 * @param id
	 * @return boolean
	 */
	public boolean exists(int id) {
		return pathRepository.existsPathByIdEquals(id);
	}

	/**
	 * create and save a new path
	 * @param newPath
	 * @return Path
	 */
	public Path createPath(Path newPath) throws Exception {
		Path pathToCreate = null;
		if(newPath != null){
			if(newPath.getFrom() != null && newPath.getTo() != null){
				try{
					pathToCreate = pathRepository.save(newPath);
				}catch (Exception iae){
					iae.printStackTrace();
					throw  new Exception("Technical Error: "+iae.getLocalizedMessage());
				}
			}else{
				throw new IllegalArgumentException("Cannot create path without its from and to Points");
			}
		}else {
			throw new IllegalArgumentException("Cannot create null path");
		}
		return pathToCreate;
	}


	private Path edit(Path pathToEdit) throws Exception {
		Path foundedPath = getPath(pathToEdit.getId());
		foundedPath.setFrom(pathToEdit.getFrom());
		foundedPath.setTo(pathToEdit.getTo());
		foundedPath.setSurveys(pathToEdit.getSurveys());
		return entityManager.merge(foundedPath);
	}


	private boolean delete(Path pathToDelete) {
		try{
			pathRepository.delete(pathToDelete);
			return true;
		}catch (HibernateException he) {
			throw new IllegalArgumentException("Unknown path to delete !");
		}
	}

}
