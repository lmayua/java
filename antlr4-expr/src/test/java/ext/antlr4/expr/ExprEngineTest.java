package ext.antlr4.expr;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ext.antlr4.expr.common.MutilRule;
import ext.antlr4.expr.common.MutilSubRule;
import ext.antlr4.expr.common.Var;
import ext.antlr4.expr.exception.ExprEvalException;
import junit.framework.TestCase;

public class ExprEngineTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void evalTest1(){
        //#childExpr
        TestCase.assertEquals(3, ExprEngine.getInstance().eval("(1+2)*3/(1+2)"));
        //#ifExpr
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("if(1>2){1!=1}else if(3>2){1==1}else{false}"));
        String content = "if( _100.101_==10000 ) { 10000 } else if ( _100.101_==20000 ) { 20000 } else { 30000 } ";
        List<Var> vars = Arrays.asList(new Var[]{new Var("100.101", "20000")});
        TestCase.assertEquals(20000, ExprEngine.getInstance().eval(content, vars));
        //#inExpr
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("abc in(abc,bcd,efg)"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("abc in(abcd,bcd,efg)"));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("极物 not in(红孩子,冰洗,B2B)"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("冰洗 not in(红孩子,冰洗,B2B)"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("冰洗 not in('红孩子','冰洗','B2B')"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("冰洗 not in(\"红孩子\",\"冰洗\",\"B2B\")"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("冰洗 not in(红孩子,冰洗,金融B2B)"));
        //#atomExpr
        //TestCase.assertEquals("_100.101_", ExprEngine.getInstance().eval("_100.101_"));
        TestCase.assertEquals("10000", ExprEngine.getInstance().eval("_100.101_", Arrays.asList(new Var[]{new Var("100.101", "10000")})));
        TestCase.assertEquals(1000.0009, ExprEngine.getInstance().eval("1000.0009"));
        TestCase.assertEquals(10000, ExprEngine.getInstance().eval("10000"));
        TestCase.assertEquals("abc", ExprEngine.getInstance().eval("'abc'"));
        TestCase.assertEquals("中文", ExprEngine.getInstance().eval("\"中文\""));
        //#powExpr
        //#unaryMinusExpr
        //#notExpr
        //#multiplicationExpr
        TestCase.assertEquals(60, ExprEngine.getInstance().eval("(100+100)*3/(30-20)"));
        TestCase.assertEquals(60, ExprEngine.getInstance().eval("(_100.101_+100)*3/(_100.102_-20)", Arrays.asList(new Var[]{new Var("100.101", 100), new Var("100.102", 30)})));
        //#additiveExpr
        //#relationalExpr
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("100>200"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("100>=200"));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("100<200"));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("100<=200"));
        //#equalityExpr
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("100!=200"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("100==200"));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("true==true"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("abc!=abc"));
        //#andExpr
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("(1<2) && (2<3) && true"));
        //#orExpr
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("(1>2) || (2>3) || false || true"));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("(1<2) && ((2>3) || true)"));
        
        //#likeExpr
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("a1bc like %a1%"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("bc like %a%"));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("cba1 like %a1"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("abc like %a"));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval("cba like a%"));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval("a1bc like a1%"));
        
        //#mutilMsgExpr
        //ExprEngine.getInstance().eval("_@(100-10>20)@_ && _@(100<200)@_");
    }
    
    @Test
    public void parseForMutilMsgTest() {
        ExprEngine.getInstance().eval("1=1");
        
        String content1 = "$[((1<2) && (2<3))] && $[((1>2) || (2>3) || false || true)]";
        MutilRule mRule1 = ExprEngine.getInstance().parseMutilMsg(content1);
        
        String content = "$[((_110202000000.100101220004_==OutputTaxParseNewUtils) && (_110202000000.100101220005_==S))] && $[((_110202000000.100101220004_==CostInfoSRVParseUtils) && (_110202000000.100101220005_==S) )]";
        long start = System.currentTimeMillis();
        MutilRule mRule = ExprEngine.getInstance().parseMutilMsg(content);
        System.out.println("cost ->" + (System.currentTimeMillis() - start));
        List<Var> vars = Arrays.asList(new Var[]{
                new Var("110202000000.100101220004", "OutputTaxParseNewUtils"), 
                new Var("110202000000.100101220005", "S")});
        List<MutilSubRule> subRules = mRule.getSubRules();
        for (MutilSubRule subRule : subRules){
            System.out.println(subRule.calculate(vars));
        }
        TestCase.assertEquals(false, mRule.calculate(Arrays.asList(new Var[] {new Var("r001", "true"), new Var("r002", "false")})));
        TestCase.assertEquals(true, mRule.calculate(Arrays.asList(new Var[] {new Var("r001", "true"), new Var("r002", "true")})));
    }
    
    /*(  _110202000000.100101220004_  ==  100101110001  )
    (  _110209000000.100101220004_  ==  100101110001  ) && ( _110209000000.100101220005_  !=  100101110003 )
    (  _110201000000.100101220004_  ==  100101120001  ) || (  _110201000000.100101220004_  ==  100101130001 ) 
    (  _110203000000.100101220004_  ==  100101110001  )  &&  (  _110201000000.100101220004_  ==  100101110001  )  &&  (  _110203000000.100101210001_  -  _110201000000.100101210001_  <=  120  )  &&  (  _110203000000.100101210001_  -  _110201000000.100101210001_  >  0  ) 
    (  _130101000000.100101220004_  ==  100101110001  )  &&  (  _130101000000.100101240002_  ==  S  )
     
    (  _130101000000.100101220009_  ==  nbillingan_commonparse_step_result_topic  )  &&  (  _130101000000.100101220022_  ==  SourcingCompanyFmdmParseUtils  )  && (  _130101000000.100101240002_  ==  EF  ) 
    (  _130101000000.100101220009_  ==  common_parse_result_info_topic  )  &&  (  _130101000000.100101240005_  !=  0 ) &&  (  _130101000000.100101240005_  !=  1 ) &&  (  _130101000000.100101240005_  !=  2 ) &&  (  _130101000000.100101240005_  !=  3 ) &&  (  _130101000000.100101240005_  !=  4 ) &&  (  _130101000000.100101240005_  !=  5 ) &&  (  _130101000000.100101240005_  !=  6 ) &&  (  _130101000000.100101240005_  !=  7 ) &&  (  _130101000000.100101240005_  !=  8 ) &&  (  _130101000000.100101240005_  !=  E ) &&  (  _130101000000.100101240005_  !=  F ) 
    (  _130101000000.100101220009_  ==  common_parse_result_info_topic  )  &&  (  _130101000000.100101240005_  not in ( 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , E , F ) ) 
    
    (  _130101000000.100101220009_  ==  nbillingan_exception_order_handle_topic  )  &&  (  _130101000000.100101220019_  ==  "原单解析失败"  ) 

    (  _130107000000.100101220004_  ==  100101110001  )  &&  (  (  _130107000000.100101240009_  ==  I  )  ||  (  _130107000000.100101240009_  ==  F  )  )
     
    (  _130103000000.100101220004_  ==  100101120001  )  &&  (  _130103000000.100101220018_  ==  1  ) && (  _130103000000.100101220019_  !=  '收款记账失败'  )  &&  (  _130103000000.100101220019_  !=  '收入成本记账失败'  )*/
    
    @Test
    public void evalTestForRules(){
        String content = "(  _110202000000.100101220004_  ==  100101110001  )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{new Var("110202000000.100101220004", "100101110001")})));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{new Var("110202000000.100101220004", "100101110002")})));
        
        content = "(  _110209000000.100101220004_  ==  100101110001  ) && ( _110209000000.100101220005_  !=  100101110003 )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{new Var("110209000000.100101220004", "100101110001"), new Var("110209000000.100101220005", "100101110002")})));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{new Var("110209000000.100101220004", "100101110001"), new Var("110209000000.100101220005", "100101110003")})));
        
        content = "(  _110201000000.100101220004_  ==  100101120001  ) || (  _110201000000.100101220004_  ==  100101130001 )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{new Var("110201000000.100101220004", "100101120001")})));
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{new Var("110201000000.100101220004", "100101130001")})));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{new Var("110201000000.100101220004", "100101130002")})));
        
        
        content = "(  _110203000000.100101220004_  ==  100101110001  )  &&  (  _110201000000.100101220004_  ==  100101110001  )  &&  (  _110203000000.100101210001_  -  _110201000000.100101210001_  <=  120  )  &&  (  _110203000000.100101210001_  -  _110201000000.100101210001_  >  0  )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{
                new Var("110203000000.100101220004", "100101110001"), 
                new Var("110201000000.100101220004", "100101110001"),
                new Var("110203000000.100101210001", "200"),
                new Var("110201000000.100101210001", "100")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{
                new Var("110203000000.100101220004", "100101110002"),
                new Var("110201000000.100101220004", "100101110001"),
                new Var("110203000000.100101210001", "200"),
                new Var("110201000000.100101210001", "100")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{
                new Var("110203000000.100101220004", "100101110001"), 
                new Var("110201000000.100101220004", "100101110002"),
                new Var("110203000000.100101210001", "200"),
                new Var("110201000000.100101210001", "100")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{
                new Var("110203000000.100101220004", "100101110001"), 
                new Var("110201000000.100101220004", "100101110002"),
                new Var("110203000000.100101210001", "20"),
                new Var("110201000000.100101210001", "100")
        })));
        
        content = "(  _130101000000.100101220004_  ==  100101110001  )  &&  (  _130101000000.100101240002_  ==  S  )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ new Var("130101000000.100101220004", "100101110001"), new Var("130101000000.100101240002", "S") })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ new Var("130101000000.100101220004", "100101110002"), new Var("130101000000.100101240002", "S") })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ new Var("130101000000.100101220004", "100101110001"), new Var("130101000000.100101240002", "M") })));
        
        content = "(  _130101000000.100101220009_  ==  nbillingan_commonparse_step_result_topic  )  &&  (  _130101000000.100101220022_  ==  SourcingCompanyFmdmParseUtils  )  && (  _130101000000.100101240002_  ==  EF  )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "nbillingan_commonparse_step_result_topic"), 
                new Var("130101000000.100101220022", "SourcingCompanyFmdmParseUtils"),
                new Var("130101000000.100101240002", "EF")
                })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "nbillingan1_commonparse_step_result_topic"), 
                new Var("130101000000.100101220022", "SourcingCompanyFmdmParseUtils"),
                new Var("130101000000.100101240002", "EF")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "nbillingan_commonparse_step_result_topic"), 
                new Var("130101000000.100101220022", "sourcingCompanyFmdmParseUtils"),
                new Var("130101000000.100101240002", "EF")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "nbillingan_commonparse_step_result_topic"), 
                new Var("130101000000.100101220022", "SourcingCompanyFmdmParseUtils"),
                new Var("130101000000.100101240002", "EFG")
        })));
        
        content = "(  _130101000000.100101220009_  ==  common_parse_result_info_topic  )  &&  (  _130101000000.100101240005_  !=  0 ) &&  (  _130101000000.100101240005_  !=  1 ) &&  (  _130101000000.100101240005_  !=  2 ) &&  (  _130101000000.100101240005_  !=  3 ) &&  (  _130101000000.100101240005_  !=  4 ) &&  (  _130101000000.100101240005_  !=  5 ) &&  (  _130101000000.100101240005_  !=  6 ) &&  (  _130101000000.100101240005_  !=  7 ) &&  (  _130101000000.100101240005_  !=  8 ) &&  (  _130101000000.100101240005_  !=  E ) &&  (  _130101000000.100101240005_  !=  F )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "9")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "0")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "1")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "E")
        })));
        
        content = "(  _130101000000.100101220009_  ==  common_parse_result_info_topic  )  &&  (  _130101000000.100101240005_  not in ( 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , E , F ) )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "9")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "0")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "1")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "common_parse_result_info_topic"), 
                new Var("130101000000.100101240005", "E")
        })));
        
        content = "(  _130101000000.100101220009_  ==  nbillingan_exception_order_handle_topic  )  &&  (  _130101000000.100101220019_  ==  \"原单解析失败\"  )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "nbillingan_exception_order_handle_topic"), 
                new Var("130101000000.100101220019", "原单解析失败")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "Nbillingan_exception_order_handle_topic"), 
                new Var("130101000000.100101220019", "原单解析失败")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130101000000.100101220009", "nbillingan_exception_order_handle_topic"), 
                new Var("130101000000.100101220019", "原单解析成功")
        })));
        
        content = "(  _130103000000.100101220004_  ==  100101120001  )  &&  (  _130103000000.100101220018_  ==  1  ) && (  _130103000000.100101220019_  !=  '收款记账失败'  )  &&  (  _130103000000.100101220019_  !=  '收入成本记账失败'  )";
        TestCase.assertEquals(true, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130103000000.100101220004", "100101120001"), 
                new Var("130103000000.100101220018", "1"),
                new Var("130103000000.100101220019", "收款记账成功")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130103000000.100101220004", "100101120001"), 
                new Var("130103000000.100101220018", "1"),
                new Var("130103000000.100101220019", "收款记账失败")
        })));
        TestCase.assertEquals(false, ExprEngine.getInstance().eval(content, Arrays.asList(new Var[]{ 
                new Var("130103000000.100101220004", "100101120001"), 
                new Var("130103000000.100101220018", "1"),
                new Var("130103000000.100101220019", "收入成本记账失败")
        })));
    }
    
    @Test
    public void evalTestForDictNoValue(){
        String content = "(  _110202000000.100101220004_  ==  100101110001  )";
        thrown.expect(ExprEvalException.class);
        thrown.expectMessage("'dict' exits none value");
        ExprEngine.getInstance().eval(content);
    }
}
