package org.wltea.analyzer.lucene;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
//lucene:4.8之前的版本
//import org.apache.lucene.util.AttributeSource.AttributeFactory;
//lucene:4.9
import org.apache.lucene.util.AttributeFactory;
import org.wltea.analyzer.cfg.Configuration;

/**
 * 
 * 功能描述: 兼容5.3.1，支持过滤器
 * 
 * @version 2.0.0
 * @author yanchangyou(15070440)
 */
public class IKTokenizerFactory extends TokenizerFactory {

    private boolean       useSmart;
    private Configuration cfg;

    public boolean useSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    public IKTokenizerFactory(Map<String, String> args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        super(args);
        this.setUseSmart("true".equals(args.get("useSmart")));
        
        if (args.get("configClass") != null) {
            cfg = (Configuration) Class.forName(args.get("configClass")).newInstance();
            cfg.setUseSmart(useSmart);
        }
        
        this.setUseSmart(true);

    }

    public Tokenizer create(AttributeFactory factory,
                            Reader input) {
        Tokenizer _IKTokenizer = new IKTokenizer(this.useSmart);
        return _IKTokenizer;
    }

    @Override
    public Tokenizer create(AttributeFactory factory) {
        Tokenizer _IKTokenizer = new IKTokenizer(cfg);
        return _IKTokenizer;
    }
}