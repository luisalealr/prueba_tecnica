package com.example.prueba_tecnica.data.model

data class ConfigReportFolio(
    val askForArea: Boolean,
    val askForDepartment: Boolean,
    val configActionPlanReportFolioEvidenceTypes: List<ConfigActionPlanReportFolioEvidenceType>,
    val configReportFolioApprover: ConfigReportFolioApprover,
    val configReportFolioReject: ConfigReportFolioReject,
    val createBy: Int,
    val createTime: String,
    val enableRatingQuestion: Boolean,
    val id: Int,
    val isAreaRequired: Boolean,
    val isDepartmentRequired: Boolean,
    val isRatingQuestionRequired: Boolean,
    val isRequired: Boolean,
    val max: Any,
    val maxRejects: Int,
    val ratingQuestion: Any,
    val reportFolioGeneral: Boolean,
    val updateTime: String
)