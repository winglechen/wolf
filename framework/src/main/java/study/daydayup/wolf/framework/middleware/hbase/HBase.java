package study.daydayup.wolf.framework.middleware.hbase;

import lombok.Setter;
import study.daydayup.wolf.framework.middleware.hbase.executor.Executor;
import study.daydayup.wolf.framework.middleware.hbase.option.*;

/**
 * HBase
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/26 16:03
 **/
public class HBase {

    private HBase(){
    }

    @Setter
    private static Executor executor;

    public static OptionBuilder get(GetOptionCriteria optionCriteria){
        return toOption(optionCriteria);
    }

    public static OptionBuilder gets(OptionCriteria optionCriteria){
        return toOption(optionCriteria);
    }

    public static OptionBuilder put(PutOptionCriteria optionCriteria){
        return toOption(optionCriteria);
    }

    public static OptionBuilder puts(PutsOptionCriteria optionCriteria){
        return toOption(optionCriteria);
    }

    public static OptionBuilder scan(ScanOptionCriteria optionCriteria){
        return toOption(optionCriteria);
    }

    private static OptionBuilder toOption(OptionCriteria optionCriteria){
        OptionBuilder optionBuilder = new OptionBuilder();
        optionBuilder.option(optionCriteria);
        optionBuilder.setExecutor(executor);
        return optionBuilder;
    }



}
