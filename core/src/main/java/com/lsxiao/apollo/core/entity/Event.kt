package com.lsxiao.apollo.core.entity

/**
 * write with Apollo
 * author:lsxiao
 * date:2017-05-24 10:36
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */
class Event {
    var tag: String? = null
    var data: Any? = null
    var pid = 0
    var isSticky = false

    constructor() {}
    constructor(tag: String?, data: Any?, pid: Int) {
        this.tag = tag
        this.data = data
        this.pid = pid
    }

    constructor(tag: String?, data: Any?, pid: Int, isSticky: Boolean) {
        this.tag = tag
        this.data = data
        this.pid = pid
        this.isSticky = isSticky
    }
}