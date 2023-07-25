package com.trajectory27.flowbasicsdemo

/**
 * @author Trajectory27
 * @description
 * @date 2023/7/24 17:14
 */
data class ProfileState(
    val proFilePicUrl: String? = null,
    val userName: String? = null,
    val description: String? = null,
    val posts: List<Post> = emptyList()
)
