package com.mte.infrastructurebase.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.mte.infrastructurebase.data.entities.Match

@Dao
interface SportDao {

    @Query("SELECT * FROM matches WHERE idLeague = :idLeague  ORDER BY dateEvent DESC LIMIT 15")
    fun getNextMatches(idLeague: String?): LiveData<List<Match>>


}