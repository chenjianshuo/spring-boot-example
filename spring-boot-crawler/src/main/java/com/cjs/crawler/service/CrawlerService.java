package com.cjs.crawler.service;

import com.cjs.crawler.cache.DataCache;
import com.cjs.crawler.domin.Blog;
import com.cjs.crawler.utils.MD5Utils;
import com.github.vector4wang.VWCrawler;
import com.github.vector4wang.proxy.AbstractProxyExtractor;
import com.github.vector4wang.proxy.Proxy2;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianshuo on 2018/8/14 11:21.
 */
@Component
public class CrawlerService implements CommandLineRunner{

    @Autowired
    private DataCache dataCache;


    @Override
    public void run(String... strings) throws Exception {
        /*List<Proxy2> ps = new ArrayList<>();
        Proxy2 proxy21 = new Proxy2("45.32.119.145",1008);


        ps.add(proxy21);*/

        new VWCrawler.Builder().setUrl("https://blog.csdn.net/qqHJQS")
                .setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36")
                .setTargetUrlRex("https://blog.csdn.net/qqHJQS/article/details/[0-9]+")
                .setTargetUrlRex("https://blog.csdn.net/qqhjqs/article/details/[0-9]+")
                /*.setProxys(ps)
                .setAbsProxyExtracter(new AbstractProxyExtractor() {
                    @Override
                    public Proxy extractProxyIp() {
                        Proxy2 proxy2 = getProxy2s().get(0);
                        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy2.getIp(), proxy2.getPort())) ;
                    }
                })*/
                .setTimeOut(5000)
                .setThreadCount(5)
                .setPageParser(new com.github.vector4wang.service.CrawlerService<Blog>() {
                    @Override
                    public void parsePage(Document document, Blog pageObj) {
                        pageObj.setReadCount(Integer.parseInt(pageObj.getReadCountStr().replace("阅读数：", "")));
                        pageObj.setUrl(document.baseUri());
                        pageObj.setUrlMd5(MD5Utils.md5(pageObj.getUrl()));
                    }

                    @Override
                    public void save(Blog pageObj) {
                        dataCache.save(pageObj);
                    }
                }).build().start();

    }
}
