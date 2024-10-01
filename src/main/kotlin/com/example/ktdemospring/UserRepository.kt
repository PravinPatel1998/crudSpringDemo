package com.example.ktdemospring

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository : MongoRepository<User, String> {
    @Query("{ 'username': { \$regex: ?0, \$options: 'i' } }")
    fun findByUsernameContainingIgnoreCase(username: String): List<User>
}