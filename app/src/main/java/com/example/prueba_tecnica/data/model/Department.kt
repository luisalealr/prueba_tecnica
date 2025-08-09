package com.example.prueba_tecnica.data.model

data class Department(
    val description: Any,
    val id: Int,
    val name: String,
    val userManage: UserManage,
    val userManageId: Int,
    val userManagers: List<UserManager>
)