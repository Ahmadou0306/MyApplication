package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.dao.MemberDao
import com.example.myapplication.projetmobile.dataSource.models.Member
import kotlinx.coroutines.Dispatchers



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

    fun getMemberById(id:Int)=memberDao.getMemberById(id)

}