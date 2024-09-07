package com.submission.valorantagentandroid.core.data.source.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agent")
data class AgentEntity(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    var uuid: String,

    @ColumnInfo(name = "displayName")
    var displayName: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "developerName")
    var developerName: String?,

    @ColumnInfo(name = "displayIcon")
    var displayIcon: String?,

    @ColumnInfo(name = "fullPortrait")
    var fullPortrait: String?,

    @ColumnInfo(name = "backgroundGradientColors")
    var backgroundGradientColors: List<String?>?,

    @ColumnInfo(name = "background")
    var background: String?,

    @ColumnInfo(name = "isPlayableCharacter")
    var isPlayableCharacter: Boolean?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
