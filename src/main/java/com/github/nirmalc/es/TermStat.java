package com.github.nirmalc.es;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.xcontent.ToXContent;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;

import java.io.IOException;

public class TermStat implements Writeable, ToXContent {

    private long termFrequency = 0;
    private long docFrequency = 0;
    private String term;

    public TermStat() {
    }

    public TermStat(String term, long termFrequency, long docFrequency) {
        this.term = term;
        this.termFrequency = termFrequency;
        this.docFrequency = docFrequency;
    }

    public TermStat(StreamInput in) throws IOException {
        termFrequency = in.readLong();
        docFrequency = in.readLong();
    }

    public void add(TermStat termStat) {
        this.termFrequency += termStat.termFrequency;
        this.docFrequency += termStat.docFrequency;
    }

    public long getTermFrequency() {
        return termFrequency;
    }

    public long getDocFrequency() {
        return docFrequency;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeLong(termFrequency);
        out.writeLong(docFrequency);
    }

    public String toString() {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            toXContent(builder, EMPTY_PARAMS);
            builder.endObject();
            return builder.toString();
        } catch (IOException e) {
            return "{ \"error\" : \"" + e.getMessage() + "\"}";
        }
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field("termFrequency", termFrequency);
        builder.field("docFrequency", docFrequency);
        return builder;
    }
}
