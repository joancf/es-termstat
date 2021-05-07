package com.github.nirmalc.es;

import org.elasticsearch.action.support.broadcast.BroadcastShardResponse;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.index.shard.ShardId;

import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;

public class ShardTermStatsResponse extends BroadcastShardResponse {
    private Map<String, TermStat> results;

    public ShardTermStatsResponse(ShardId shardId, Map<String, TermStat> results) {
        super(shardId);
        this.results = results;
    }

    public Map<String, TermStat> getResults() {
        return results;
    }

    public ShardTermStatsResponse(StreamInput in) throws IOException {
        super(in);
        int n = in.readInt();
        results = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String text = in.readString();
            TermStat t = new TermStat(in);
            results.put(text, t);
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeInt(results.size());
        for (Map.Entry<String, TermStat> t : results.entrySet()) {
            out.writeString(t.getKey());
            t.getValue().writeTo(out);
        }
    }
}
