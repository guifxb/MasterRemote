package com.example.masterremote.data

import com.example.masterremote.R

data class Feature(
    val title: String,
    val icon: Int
)

val features = listOf(
    Feature("Acesso Remoto", R.drawable.acesso_remoto),
    Feature("Backups Programáveis", R.drawable.backups_programaveis),
    Feature("Bloqueio de Recursos", R.drawable.bloqueio_de_recursos),
    Feature("Gravação Automática", R.drawable.gravacao_automatica),
    Feature("Manutenção Remota", R.drawable.manutencao_remota),
    Feature("Monitoramento", R.drawable.monitoramento),
)