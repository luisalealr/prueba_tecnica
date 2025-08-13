package com.example.prueba_tecnica.data.model

data class ConfigReportFolioReject(
    val evidenceType: EvidenceType,
    val evidenceTypeId: Int,
    val paramsConfiguration: ParamsConfiguration,
    val required: Boolean
)