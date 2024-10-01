package com.example.ktdemospring
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun registerUser(user: User): User {
        val encodedPassword = passwordEncoder.encode(user.password)
        val userToSave = user.copy(password = encodedPassword)
        return userRepository.save(userToSave)
    }

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: String): Optional<User> = userRepository.findById(id)

    fun updateUser(id: String, user: User): Optional<User> {
        return if (userRepository.existsById(id)) {
            userRepository.save(user.copy(id = id)).let { Optional.of(it) }
        } else {
            Optional.empty()
        }
    }

    fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }

    fun searchUsers(username: String): List<User> {
        return userRepository.findByUsernameContainingIgnoreCase(username)
    }
}