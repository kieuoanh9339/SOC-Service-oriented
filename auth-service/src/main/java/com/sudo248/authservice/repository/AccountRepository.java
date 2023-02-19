package com.sudo248.authservice.repository;

import com.sudo248.authservice.repository.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "SELECT * FROM account WHERE account.email = :email LIMIT 1", nativeQuery = true)
    Account getUserByEmail(@Param("emailOrPhoneNumber") String email);
//SELECT EXISTS (SELECT * FROM account WHERE account.emailOrPhoneNumber = :emailOrPhoneNumber LIMIT 1)
    @Query(value =
            "SELECT " +
            "   CASE WHEN EXISTS(" +
            "       SELECT 1 FROM account" +
            "       WHERE account.email = :email " +
            "       LIMIT 1" +
            "   )" +
            "   THEN 'true'" +
            "   ELSE 'false'" +
            "   END"
            , nativeQuery = true)
    boolean existsByEmail(@Param("emailOrPhoneNumber") String email);
}
