package com.example.prueba_tecnica.data.model

data class ConfigActionPlanReportFolioEvidenceType(
    val configActionPlanReportFolioId: Int,
    val createTime: String,
    val description: String,
    val evidenceType: EvidenceType,
    val evidenceTypeId: Int,
    val id: Int,
    val paramsConfiguration: ParamsConfiguration,
    val required: Boolean,
    val type: String,
    val updateTime: String
)