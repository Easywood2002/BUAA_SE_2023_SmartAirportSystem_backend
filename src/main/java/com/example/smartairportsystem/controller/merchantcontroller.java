package com.example.smartairportsystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/merchant")
public class merchantcontroller {
    @Resource
    private merchantservice merchantService = new merchantserviceimpl();
    @Resource
    private commodityservice commodityService = new commodityserviceimpl();

    //商户注册功能
    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map<String,Object> logupMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String realname = rawmap.get("realname");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");
        String shopname = rawmap.get("shopname");
        String email = rawmap.get("email");
        String idnumber = rawmap.get("idnumber");

        try{
            if(repasswords.equals(passwords)) {
                //对商户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                merchant exist_ID = merchantService.getMerchantByIDnumber(idnumber);
                merchant exist_name = merchantService.getMerchantByEmail(email);
                if (exist_ID != null) {
                    map.put("success", false);
                    map.put("message", "该身份证号已被注册！");
                else if (exist_name != null) {
                    map.put("success", false);
                    map.put("message", "该邮箱已被注册！");
                } else {
                    merchantService.logupNewMerchant(new merchant(0,realname,idnumber,passwords+salt,salt,shopname,email));
                    map.put("success", true);
                    map.put("message", "用户注册成功！");
                }
            }else{
                map.put("success", false);
                map.put("message", "确认密码不一致！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户注册失败！");
        }
        return map;
    }

    //商户登录功能
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> loginMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();
        map.put("token", "null");

        //表单取参
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");

        try{
            merchant exist = merchantService.getMerchantByEmail(email);
            if (exist != null) {
                //取出用户盐值，与当前输入的密码拼接加密后再与数据库中的信息进行比较
                String inpwd = securityService.SHA1(passwords+exist.getSalt());
                if(inpwd.equals(exist.getPasswords())) {
                    //将用户id经md5加密后作为token一并返回前端，便于后续访问
                    String merchanttk = securityService.MD5(exist.getMerchantByID().toString());
                    token newtk = new token(exist.getMerchantByID(),merchanttk);
                    token existtk = tokenService.getTokenByID(newtk.getId(), TokenTypeUtil.MERCHANT);
                    if (existtk == null){
                        tokenService.loginNewToken(newtk, TokenTypeUtil.MERCHANT);
                    }else{
                        tokenService.updateOldToken(newtk, TokenTypeUtil.MERCHANT);
                    }
                    map.put("success", true);
                    map.put("message", "用户登录成功！");
                    map.put("token",merchanttk);
                }else {
                    map.put("success", false);
                    map.put("message", "用户密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("message", "用户不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户登录失败！");
        }
        return map;
    }

    //列出该商户的实名信息功能
    @RequestMapping(value = "/listmerchant",method = RequestMethod.POST)
    public Map<String,Object> listMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<merchant> rtlist = merchantService.listMerchantByMerchantid(tokenentity.getId());
                map.put("success", true);
                map.put("message", rtlist);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }

    //商户修改信息功能
    @RequestMapping(value = "/updatemerchant",method = RequestMethod.POST)
    public Map<String,Object> updateMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String merchantid = rawmap.get("merchantid");
        String realname = rawmap.get("realname");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");
        String shopname = rawmap.get("shopname");
        String email = rawmap.get("email");
        String idnumber = rawmap.get("idnumber");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                merchant conflict_email = merchantService.getMerchantByEmail(email);
                merchant conflict_shop = merchantService.getMerchantByShopname(shopname);
                if(conflict_email != null){
                    map.put("success", false);
                    map.put("message", "该邮箱已被使用！");
                }else if(conflict_shop != null){
                    map.put("success", false);
                    map.put("message", "该店名已被使用！");
                }else {
                    personService.updateOldMerchant(new merchant(Integer.parseInt(merchantid),realname,idnumber,passwords+salt,salt,shopname,email));
                    map.put("success", true);
                    map.put("message", "商户信息已更新！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改商户信息失败！");
        }
        return map;
    }

    //商户注销账户功能
    @RequestMapping(value = "/removemerchant",method = RequestMethod.POST)
    public Map<String,Object> removeMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String merchantid = rawmap.get("merchantid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                merchantService.removeOldMerchant(Integer.parseInt(merchantid));
                map.put("success", true);
                map.put("message", "商户信息已注销！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "商户信息注销失败！");
        }
        return map;
    }

    //商户提交入驻请求
    @RequestMapping(value = "/addmerchantrequest", method = RequestMethod.POST)
    public Map<String, Object> addMerchantrequest(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String realname = rawmap.get("realname");
        String nickname = rawmap.get("nickname");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");
        String email = rawmap.get("email");

        try{
            if(repasswords.equals(passwords)) {
                //对用户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                merchantrequest exist = merchantrequestService.getMerchantrequestByEmail(email);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "请勿重复提交请求！");
                } else {
                    merchantrequestService.logupNewMerchantrequest(new merchantrequest(0,realname,nickname,passwords+salt,salt,email));
                    map.put("success", true);
                    map.put("message", "商户入驻请求提交成功！");
                }
            }else{
                map.put("success", false);
                map.put("message", "确认密码不一致！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","商户入驻请求提交失败！");
        }

        return map;
    }
    
    //商户添加商品信息功能
    @RequestMapping(value = "/addcommodity", method = RequestMethod.POST)
    public Map<String, Object> addCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String name = rawmap.get("name");
        String merchantid = rawmap.get("mercantid")
        String counts = rawmap.get("counts");
        String price = rawmap.get("price");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                commoditylist exist = commodityService.getCommodityByCombine(Integer.parseInt(merchantid),name);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "商品信息已存在！");
                } else {
                    commodityService.addNewCommodity(new commoditylist(0,name,Integer.parseInt(mercantid), Integer.parseInt(counts), Double.parseDouble(price)));
                    map.put("success", true);
                    map.put("message", "添加商品信息成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加商品信息失败！");
        }
        return map;
    }

    //商户修改商品信息功能
    @RequestMapping(value = "/updatecommodity", method = RequestMethod.POST)
    public Map<String, Object> updateCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String commodityid = rawmap.get("commodityid");
        String name = rawmap.get("name");
        String merchantid = rawmap.get("mercantid")
        String counts = rawmap.get("counts");
        String price = rawmap.get("price");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                commoditylist conflict = commodityService.getCommodityByCombine(commodityService.getMerchantByCommodity(Integer.parseInt(commodityid)),name);
                if(conflict != null){
                    map.put("success", false);
                    map.put("message", "已存在相同商品信息！");
                }else{
                    commodityService.updateOldCommodity(new commoditylist(Integer.parseInt(commodityid), name, Integer.parseInt(mercantid), Integer.parseInt(counts), Double.parseDouble(price)));
                    map.put("success", true);
                    map.put("message", "商品信息已更新！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改商品信息失败！");
        }
        return map;
    }

    //商户删除商品信息功能
    @RequestMapping(value = "/removecommodity", method = RequestMethod.POST)
    public Map<String, Object> removeCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String commodityid = rawmap.get("commodityid");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                commodityService.removeOldCommodity(Integer.parseInt(commodityid));
                map.put("success", true);
                map.put("message", "商户信息已删除！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除商户信息失败！");
        }
        return map;
    }

    //商户查询自身的商品清单
    @RequestMapping(value = "/listcommodity",method = RequestMethod.POST)
    public Map<String,Object> listCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String commodityid = rawmap.get("commodityid");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<commoditylist> rtlist = commodityService.listCommodityByMerchantid(tokenentity.getId());
                map.put("success", true);
                map.put("message", rtlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }
}
