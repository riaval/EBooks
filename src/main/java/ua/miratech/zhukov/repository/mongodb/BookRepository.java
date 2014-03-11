package ua.miratech.zhukov.repository.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;

import java.util.List;

/**
 * Spring Data repository for {@link ua.miratech.zhukov.domain.Book} Book
 */
@Repository
public interface BookRepository extends CrudRepository<Book, String> {

	/**
	 * Returns book from DMS by MD5 hash
	 *
	 * @param md5 MD5 hash
	 * @return {@link ua.miratech.zhukov.domain.Book} Book
	 */
	@Query("{ md5: ?0 }" )
	Book findByMD5(String md5);

	/**
	 * Returns books from DMS by Owner id
	 *
	 * @param ownerId id owner
	 * @param pageable Pageable options
	 * @return {@link ua.miratech.zhukov.domain.Book} Books
	 */
	@Query("{ owner.$id: ?0 }")
	Page<Book> findBooksByOwner(ObjectId ownerId, Pageable pageable);

	/**
	 * Returns books from DMS if they: public or shared for current User
	 *
	 * @param currentUserId User id
	 * @param pageable Pageable options
	 * @return {@link ua.miratech.zhukov.domain.Book} Books
	 */
	@Query("{ $or: [ " +
				"{ sharedType: 'PUBLIC' }, " +
				"{ $and: [ { sharedFor.$id: ?0 }, { sharedFor.$id: { $exists: true } } ] } " +
			"] }")
	Page<Book> findLastBooks(ObjectId currentUserId, Pageable pageable);

	/**
	 * Returns books from DMS if they: (in list of proposed indexes) AND
	 * (public OR belong to current User OR shared for current User)
	 *
	 * @param currentUserId User id
	 * @param storedIndexes indexes in File Storage
	 * @param pageable Pageable options
	 * @return {@link ua.miratech.zhukov.domain.Book} Books
	 */
	@Query("{ $and: [ " +
				"{ storedIndex: { $in: ?1 } }, " +
				"{ $or: [ " +
					"{ sharedType: 'PUBLIC' }, " +
					"{ owner.$id: ?0 }, " +
					"{ $and: [ " +
						"{ sharedFor.$id: ?0 }, " +
						"{ sharedFor.$id: { $exists: true } } " +
					"] } " +
				"] } " +
			"] }")
	Page<Book> findBooksWithStoredIndexes(ObjectId currentUserId, List<String> storedIndexes, Pageable pageable);

}