package SSH.eservices.web.services.Itf;

import SSH.eservices.model.Path;
import SSH.eservices.web.dto.PathTO;

import java.util.List;

public interface IPathService {
	/**
	 * get a path associate to id
	 *
	 * @param id
	 * @return
	 * @throws Exception if path not found
	 */
	Path get(int id) throws Exception;

	/**
	 * get list of all path
	 *
	 * @return
	 * @throws Exception
	 */
	List<Path> getAll() throws Exception;

	/**
	 * create and save a new path
	 *
	 * @param pathTO
	 * @return
	 * @throws Exception
	 */
	Path create(PathTO pathTO) throws Exception;

	/**
	 * edit a path
	 *
	 * @param pathTo
	 * @return
	 * @throws Exception
	 */
	Path edit(PathTO pathTo) throws Exception;

	/**
	 * delete a path
	 *
	 * @param id
	 * @return
	 * @throws Exception if path not found
	 */
	boolean delete(int id) throws Exception;

	boolean exists(int pathId);

	Path getPath(int pathId) throws Exception;

	void save(Path path) throws Exception;
}
