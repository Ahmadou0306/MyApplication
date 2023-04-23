package com.example.myapplication.projetmobile.viewsmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.projetmobile.Graph
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class MemberViewModel(private val memberDataSource: MemberRepository = Graph.memberRepo): ViewModel() {
    private val _state = MutableStateFlow(MemberViewState())
    val state: StateFlow<MemberViewState>
        get() = _state

     private val memberList = memberDataSource.readAllMember
    private val memberSelected = MutableStateFlow(_state.value.memberSelected)
    init {
        viewModelScope.launch {
            combine(memberList, memberSelected) { list: List<Member>, selected: Boolean ->
                MemberViewState(list, selected)
            }.collect {
                _state.value = it
            }
        }
    }
    fun addMember(member: Member) = viewModelScope.launch {
        memberDataSource.addMember(member)
    }
    fun deleteMember(member: Member) = viewModelScope.launch {
        memberDataSource.deleteMember(member)
    }
    fun getMemberById(id: Int): Flow<Member?> = memberDataSource.getMemberById(id).flatMapLatest { member ->
        val states = _state.value.copy(memberSelected = member != null)
        _state.value = states
        memberSelected.value = member != null
        flowOf(member)
    }
    data class MemberViewState(
        val memberList: List<Member> = emptyList(),
        val memberSelected: Boolean = false,
    )
}