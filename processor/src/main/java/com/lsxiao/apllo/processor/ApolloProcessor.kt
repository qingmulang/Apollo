package com.lsxiao.apllo.processor

import com.google.auto.common.BasicAnnotationProcessor
import com.google.auto.service.AutoService
import com.google.common.collect.ImmutableSet
import com.lsxiao.apllo.processor.CodeGenerator.Companion.create
import com.lsxiao.apllo.processor.step.*
import java.util.*
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element

@AutoService(Processor::class)
class ApolloProcessor : BasicAnnotationProcessor() {
    private var mGenerated = false
    override fun initSteps(): Iterable<ProcessingStep> {
        return ImmutableSet.of(
                ReceiveStep(),
                TakeStep(),
                StickyStep(),
                BackpressureStep(),
                SubscribeStep(),
                ObserveStep()
        )
    }

    override fun postRound(roundEnv: RoundEnvironment) {
        super.postRound(roundEnv)
        if (mGenerated || sDescriptorMap.isEmpty()) {
            return
        }
        create(ArrayList(sDescriptorMap.values), processingEnv.filer).generate()
        mGenerated = true
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    companion object {
        var sDescriptorMap: HashMap<Element, ApolloDescriptor> = hashMapOf()
    }
}