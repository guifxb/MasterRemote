package com.example.masterremote.data

import com.example.masterremote.domain.Feature
import com.example.masterremote.domain.features


// Criando a base para consumir API

class FeatureRepository {
    fun getFeatures(): List<Feature> {
        return features
    }
}