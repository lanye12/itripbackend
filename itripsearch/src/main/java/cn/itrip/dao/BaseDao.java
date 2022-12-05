package cn.itrip.dao;

import cn.itrip.pojo.ItripHotelVO;
import cn.itrip.pojo.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.util.List;
import java.util.Queue;

/**
 * @author
 * @date 2022/11/22 15:20
 */
public class BaseDao {
    private static String url="http://localhost:8080/solr/HotelCore";
    HttpSolrClient httpSolrClient;
    public BaseDao(){
        httpSolrClient=new HttpSolrClient(url);
        httpSolrClient.setParser(new XMLResponseParser());
        httpSolrClient.setConnectionTimeout(500);
    }
    public List<ItripHotelVO> getlist(SolrQuery query)throws Exception{
        QueryResponse queryResponse = null;
        queryResponse = httpSolrClient.query(query);
        List<ItripHotelVO> list = queryResponse.getBeans(ItripHotelVO.class);
        return list;
    }

    public Page<ItripHotelVO> getlist1(SolrQuery query, int index, int paize)throws Exception{
        query.setStart((index-1)*6);
        query.setRows(paize);
        QueryResponse queryResponse = null;
        queryResponse = httpSolrClient.query(query);
        List<ItripHotelVO> list = queryResponse.getBeans(ItripHotelVO.class);
        SolrDocumentList solrDocuments = ((QueryResponse) queryResponse).getResults();
        Page page = new Page(index, paize, new Long(solrDocuments.getNumFound()).intValue());
        page.setRows(list);
        return page;
    }
}
