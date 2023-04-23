package com.example.myapplication.projetmobile.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.projetmobile.dataSource.models.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Query("SELECT * FROM member")
    fun getAllMember(): Flow<List<Member>>

    @Insert
    suspend fun insertMember( task: Member)

    @Query("SELECT * FROM member WHERE id = :id")
    fun getMemberById(id: Int): Flow<Member?>

    @Query("Delete From member WHERE id = :id")
    suspend fun deleteMember(id: Int)
}