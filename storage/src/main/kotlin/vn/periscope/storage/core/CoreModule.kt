package vn.periscope.storage.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.storage.core.storage.UploadFileService
import vn.periscope.storage.ports.storage.UploadFileUseCase

val coreModule = module(createdAtStart = true) {
    single {
        UploadFileService(
            transactionService = get(),
            minioClient = get(),
            minioConfig = get(),
            createFileEntryPort = get(),
        )
    } binds arrayOf(
        UploadFileUseCase::class,
    )
}