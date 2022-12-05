package cn.itrip;


import cn.itrip.pojo.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author
 * @date 2022/11/22 14:14
 */
public class TextSolr {
    private static String url="http://localhost:8080/solr/HotelCore";
    public static  void main(String[] args) throws Exception  {
        HttpSolrClient httpSolrClient = new HttpSolrClient(url);
        httpSolrClient.setParser(new XMLResponseParser());
        httpSolrClient.setConnectionTimeout(500);
        SolrQuery query = new SolrQuery("*:*");
        query.setSort("id", SolrQuery.ORDER.asc);
        query.setStart(0);
        //一页显示多少条
        query.setRows(10);
        QueryResponse queryResponse = null;
            queryResponse = httpSolrClient.query(query);
            List<ItripHotelVO> list = queryResponse.getBeans(ItripHotelVO.class);
            for (ItripHotelVO hotel:list){
                System.out.println(hotel.getId()+hotel.getHotelName()+hotel.getAddress());
            }
    }
}
