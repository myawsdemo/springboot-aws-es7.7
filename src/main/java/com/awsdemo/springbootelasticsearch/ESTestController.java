package com.awsdemo.springbootelasticsearch;

import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ESTestController {

    @Autowired
    private RestHighLevelClient client;

    @GetMapping("test")
    public void test() {

        CreateIndexRequest request = new CreateIndexRequest("java");
        // 2、设置索引的settings
        request.settings(Settings.builder().put("index.number_of_shards", 3) // 分片数
                .put("index.number_of_replicas", 2) // 副本数
        );
        // 4、 设置索引的别名
        request.alias(new Alias("it"));
        RequestOptions requestOptions = RequestOptions.DEFAULT;
        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request,requestOptions);
            // 6、处理响应
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse
                    .isShardsAcknowledged();
            System.out.println("acknowledged = " + acknowledged);
            System.out.println("shardsAcknowledged = " + shardsAcknowledged);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
