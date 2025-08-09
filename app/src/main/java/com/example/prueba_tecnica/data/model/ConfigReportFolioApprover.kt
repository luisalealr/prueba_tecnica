package com.example.prueba_tecnica.data.model

data class ConfigReportFolioApprover(
    val evidenceType: EvidenceTypeX,
    val evidenceTypeId: Int,
    val paramsConfiguration: ParamsConfigurationX,
    val required: Boolean
)