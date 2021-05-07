package com.github.nirmalc.es;

import org.elasticsearch.action.ActionType;

public class  TermStatsAction extends ActionType<TermStatsResponse> {

    public static final TermStatsAction INSTANCE = new TermStatsAction();
    public static final String NAME = "indices:info:nirmalc/termstats";

    private TermStatsAction() {
        super(NAME, TermStatsResponse::new);
    }
}
