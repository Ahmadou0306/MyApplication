package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.dao.MemberDao
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.dataSource.models.Project
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class MemberRepository(private val memberDao: MemberDao) {
    val readAllMember =  memberDao.getAllMember()
    suspend fun addMember(member: Member) {
        Dispatchers.IO.apply {
            memberDao.insertMember(member)
        }
    }

    suspend fun deleteMember(member: Member) {
        Dispatchers.IO.apply {
            memberDao.deleteMember(member.id)
        }
    }
    fun getMemberById(id:Int): Flow<Member?> {
        return memberDao.getMemberById(id)
    }
}