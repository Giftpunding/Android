package com.giftpunding.osds.ui.search.adapter

enum class Span(val span: Int) {
    NONE(-1),
    LEFT(0),
    MIDDLE(1),
    RIGHT(2);

    companion object{
        internal fun build(i: Int): Span{
            for(span in Span.values()){
                if(span.span == i){
                    return span
                }
            }
            return NONE
        }
    }
}