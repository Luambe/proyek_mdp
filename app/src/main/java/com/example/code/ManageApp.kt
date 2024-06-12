package com.example.code

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.repository.*
import com.example.code.data.source.remote.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ManageApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initRepository(baseContext)
    }

    companion object {
        lateinit var announcementRepository: DefaultAnnouncementRepository
        lateinit var attendanceRepository: DefaultAttendanceRepository
        lateinit var companyRepository: DefaultCompanyRepository
        lateinit var requestRepository: DefaultRequestRepository
        lateinit var taskDetailRepository: DefaultTaskDetailRepository
        lateinit var taskRepository: DefaultTaskRepository
        lateinit var userRepository: DefaultUserRepository

        fun initRepository(context: Context) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl("http://192.168.18.5:3000/") //Punya Rama
//                .baseUrl("http://192.168.1.3:3000/") //Punya Nick
    //            .baseUrl("http://192.168.1.3:3000/") //Punya steven

//                .baseUrl("http://192.168.1.5:3000/") //Punya Melvin
                .build()


            // Initialize services for each repository
            val announcementService = retrofit.create(AnnouncementService::class.java)
            val attendanceService = retrofit.create(AttendanceService::class.java)
            val companyService = retrofit.create(CompanyService::class.java)
            val requestService = retrofit.create(RequestService::class.java)
            val taskDetailService = retrofit.create(TaskDetailService::class.java)
            val taskService = retrofit.create(TaskService::class.java)
            val userService = retrofit.create(UserService::class.java)

            val roomDatabase = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "db_proyek_mdp5"
            ).build()

            // Initialize repositories with the respective services and database DAOs
            announcementRepository = DefaultAnnouncementRepository(roomDatabase, announcementService)
            attendanceRepository = DefaultAttendanceRepository(roomDatabase, attendanceService)
            companyRepository = DefaultCompanyRepository(roomDatabase, companyService)
            requestRepository = DefaultRequestRepository(roomDatabase, requestService)
            taskDetailRepository = DefaultTaskDetailRepository(roomDatabase, taskDetailService)
            taskRepository = DefaultTaskRepository(roomDatabase, taskService)
            userRepository = DefaultUserRepository(roomDatabase, userService)
        }
    }
}
