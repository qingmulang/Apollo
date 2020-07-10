package com.lsxiao.apollo.demo.model

/**
 * write with Apollo
 * author:lsxiao
 * date:2017-04-24 00:04
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */
class User {
    var name: String? = null

    constructor() {}
    constructor(name: String?) {
        this.name = name
    }

    override fun toString(): String {
        return "User{" +
                "name='" + name + '\'' +
                '}'
    }
}