package com.gk.secondhand.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.secondhand.entity.*;
import com.gk.secondhand.service.impl.*;
import com.gk.secondhand.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {


    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private ImageServiceImpl imageService;
    @Autowired
    private CatelogServiceImpl catelogService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PurseServiceImpl purseService;
    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping(value = "/homeGoods")
    public String homeGoods(Model m,HttpServletRequest request) throws Exception {
        // 商品种类数量
        int catelogSize = 7;
        // 每个种类显示商品数量
        int goodsSize = 6;
        List<Goods> goodsList = null;
        List<GoodsExtend> goodsAndImage = null;
        /* 获取最新发布列表 */
        QueryWrapper<Goods> q=new QueryWrapper<>();
        q.eq("status",1).orderByDesc("polish_time").last("limit 0,6");
        goodsList=goodsService.list(q);
       // goodsList = goodsService.getGoodsOrderByDate(goodsSize);
        goodsAndImage = new ArrayList<GoodsExtend>();
        for (int j = 0; j < goodsList.size(); j++) {
            // 将用户信息和image信息封装到GoodsExtend类中，传给前台
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(j);
            QueryWrapper<Image> q2=new QueryWrapper<>();
            q2.eq("goods_id",goods.getId());
            List<Image> images=imageService.list(q2);
            //System.out.println(images.get(j));
            //List<Image> images = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(images);
            goodsAndImage.add(j, goodsExtend);
            //System.out.println(images);
        }

        m.addAttribute("catelogGoods", goodsAndImage);
        /* 获取其他列表物品信息 */
        for (int i = 1; i <= catelogSize; i++) {
            QueryWrapper<Goods> qOther=new QueryWrapper<>();
            qOther.eq("catelog_id",i);
            qOther.eq("status",1);
            qOther.orderByDesc("polish_time").last("limit 0,6");
            goodsList=goodsService.list(qOther);
            //goodsList = goodsService.getGoodsByCatelogOrderByDate(i, goodsSize);
            goodsAndImage = new ArrayList<GoodsExtend>();
            for (int j = 0; j < goodsList.size(); j++) {
                // 将用户信息和image信息封装到GoodsExtend类中，传给前台
                GoodsExtend goodsExtend = new GoodsExtend();
                Goods goods = goodsList.get(j);
                QueryWrapper<Image> q2=new QueryWrapper<>();
                q2.eq("goods_id",goods.getId());
                List<Image> images=imageService.list(q2);
               // List<Image> images = imageService.getImagesByGoodsPrimaryKey(goods.getId());
                goodsExtend.setGoods(goods);
                goodsExtend.setImages(images);
                goodsAndImage.add(j, goodsExtend);
            }
            String key = "catelog" + "Goods" + i;
           m.addAttribute(key,goodsAndImage);

        }
        User cur_user = (User) request.getSession().getAttribute("cur_user");
       if (cur_user!=null){
          int unreadNum=messageService.unreadMessage(cur_user.getId());
          m.addAttribute("unreadNum",unreadNum);
       }
        //modelAndView.setViewName("goods/homeGoods");
        return "goods/homeGoods";
    }


    /**
     * 查询该类商品
     *
     * @param id
     *            要求该参数不为空
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/catelog/{id}")
    public String  catelogGoods(Model m,HttpServletRequest request, @PathVariable("id") Integer id,
                                     @RequestParam(value = "str", required = false) String str) throws Exception {

        /*QueryWrapper<Goods> q=new QueryWrapper<>();
        q.eq("catelog_id",id);
        List<Goods> goodsList =goodsService.list(q);*/
        List<Goods> goodsList = goodsService.getGoodsByCatelog(id,str,str);
        QueryWrapper<Catelog> qc=new QueryWrapper<>();
        qc.eq("id",id);
        Catelog catelog = catelogService.getOne(qc);
        List<GoodsExtend> goodsExtendList = new ArrayList<GoodsExtend>();
        for (int i = 0; i < goodsList.size(); i++) {
           GoodsExtend goodsExtend = new GoodsExtend();
           Goods goods = goodsList.get(i);
           QueryWrapper<Image> q2=new QueryWrapper<>();
            q2.eq("goods_id",goods.getId());
            List<Image> images=imageService.list(q2);
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(images);
            goodsExtendList.add(i, goodsExtend);
        }
       m.addAttribute("goodsExtendList", goodsExtendList);
       m.addAttribute("catelog", catelog);
       m.addAttribute("search", str);
        return "/goods/catelogGoods";
    }

    /**
     * 查询该类商品
     *
     * @param要求该参数不为空
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/catelog")
    public String homeGoods(Model m,HttpServletRequest request, @RequestParam(value = "str", required = false) String str)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        // 每个种类显示商品数量
        int goodsSize = 12;
        List<Goods> goodsList = null;
        List<GoodsExtend> goodsAndImage = null;
        /* 获取最新发布列表 */
        goodsList = goodsService.getGoodsByStr(goodsSize, str, str);
        goodsAndImage = new ArrayList<GoodsExtend>();
        for (int j = 0; j < goodsList.size(); j++) {
            // 将用户信息和image信息封装到GoodsExtend类中，传给前台
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(j);
            List<Image> images = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(images);
            goodsAndImage.add(j, goodsExtend);
        }
        Catelog catelog=new Catelog();
        catelog.setName("最新发布");
        m.addAttribute("goodsExtendList", goodsAndImage);
        m.addAttribute("search", str);
        m.addAttribute("catelog", catelog);
        return "/goods/catelogGoods";
    }

    /**
     * 搜索商品
     *
     * @param str          //ajax传值
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search")
    public ModelAndView searchGoods(@RequestParam(value = "str", required = false) String str) throws Exception {
        List<Goods> goodsList = goodsService.searchGoods(str, str);
        List<GoodsExtend> goodsExtendList = new ArrayList<GoodsExtend>();
        for (int i = 0; i < goodsList.size(); i++) {
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(i);
            List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(imageList);
            goodsExtendList.add(i, goodsExtend);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtendList", goodsExtendList);
        modelAndView.addObject("search", str);
        modelAndView.setViewName("/goods/searchGoods");
        return modelAndView;
    }
    /**
     * 根据商品id查询该商品详细信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goodsId/{id}")
    public ModelAndView getGoodsById(HttpServletRequest request, @PathVariable("id") Integer id,
                                     @RequestParam(value = "str", required = false) String str) throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        System.out.println(goods);
        User seller = userService.selectByPrimaryKey(goods.getUserId());
        Catelog catelog = catelogService.selectByPrimaryKey(goods.getCatelogId());
        GoodsExtend goodsExtend = new GoodsExtend();
        List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(id);
        CommentExtend CommentExtend=goodsService.selectCommentsByGoodsId(id);
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("CommentExtend",CommentExtend);
        modelAndView.addObject("goodsExtend", goodsExtend);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("search", str);
        modelAndView.addObject("catelog", catelog);
        modelAndView.setViewName("/goods/detailGoods");
        return modelAndView;

    }

    /**
     * 发布评论
     * @return
     */
    @RequestMapping(value = "/addComments",method=RequestMethod.POST)
    @Transactional
    @ResponseBody
    public void deleteFocus(HttpServletRequest request,Comments comments) {
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        comments.setUser(cur_user);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date createAt =new Date();
        comments.setCreateAt(sdf.format(createAt));
        goodsService.addComments(comments);
        String t = DateUtil.getNowDate();
        Message message=new Message();
        Goods goods=goodsService.getGoodsByPrimaryKey(comments.getGoods().getId());
        message.setUserid(goods.getUserId());
        message.setMessage("您的 "+goods.getName()+" 物品用户 "+cur_user.getUsername()+" 有新的留言了");
        message.setMessageData(t);
        message.setIsRead(0);
        messageService.noticeBuy(message);

    }

    /**
     * 发布商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoods")
    public ModelAndView publishGoods(HttpServletRequest request) {
        // 可以校验用户是否登录
        User cur_user = (User) request.getSession().getAttribute("cur_user");
        // if (cur_user == null) {
        // return "/goods/homeGoods";
        // } else {
        Integer userId = cur_user.getId();
        Purse myPurse = purseService.getPurseByUserId(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("myPurse", myPurse);
        mv.setViewName("/goods/pubGoods");
        return mv;
    }

    /**
     * 提交发布的商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoodsSubmit")
    public String publishGoodsSubmit(HttpServletRequest request, Image ima, Goods goods, MultipartFile image)
            throws Exception {
        // 查询出当前用户cur_user对象，便于使用id
        User cur_user = (User) request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        goodsService.addGood(goods, 10);// 在goods表中插入物品
        // 返回插入的该物品的id
        int goodsId = goods.getId();
        ima.setGoodsId(goodsId);
        imageService.insert(ima);// 在image表中插入商品图片
        // 发布商品后，catlog的number+1，user表的goods_num+1，更新session的值
        int number = cur_user.getGoodsNum();
        Integer calelog_id = goods.getCatelogId();
        Catelog catelog = catelogService.selectByPrimaryKey(calelog_id);
        catelogService.updateCatelogNum(calelog_id, catelog.getNumber() + 1);
        userService.updateGoodsNum(cur_user.getId(), number + 1);
        cur_user.setGoodsNum(number + 1);
        request.getSession().setAttribute("cur_user", cur_user);// 修改session值
        return "redirect:/user/allGoods";
    }

    /**
     * 上传物品
     *
     * @param session
     * @param myfile
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public Map<String, Object> uploadFile(HttpSession session, MultipartFile myfile)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
        // 存储图片的物理路径
        //String file_path = session.getServletContext().getRealPath("upload");
        String file_path= ClassUtils.getDefaultClassLoader().getResource("").getPath();
        // System.out.println("file_path:"+file_path);
        // 上传图片
        if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
            // 新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            // 新图片
            File newFile = new File(file_path + "/static/upload/" + newFileName);
            // 将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            // 将新图片名称返回到前端
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "成功啦");
            map.put("imgUrl", newFileName);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("error", "图片不合法");
            return map;
        }
    }
    /**
     * 修改商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoods/{id}")
    public ModelAndView editGoods(HttpServletRequest request,@PathVariable("id") Integer id) throws Exception {
        User cur_user = (User) request.getSession().getAttribute("cur_user");
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(id);
        GoodsExtend goodsExtend = new GoodsExtend();
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = cur_user.getId();
        Purse myPurse = purseService.getPurseByUserId(userId);
        modelAndView.addObject("myPurse", myPurse);
        // 将商品信息添加到model
        modelAndView.addObject("goodsExtend", goodsExtend);
        modelAndView.setViewName("/goods/editGoods");
        return modelAndView;
    }
    /**
     * 提交商品更改信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoodsSubmit")
    public String editGoodsSubmit(HttpServletRequest request, Goods goods) throws Exception {
        User cur_user = (User) request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        String polish_time = DateUtil.getNowDay();
        goods.setPolishTime(polish_time);
        goods.setStatus(1);
        goodsService.updateGoodsByPrimaryKeyWithBLOBs(goods.getId(), goods);
        return "redirect:/user/allGoods";
    }
    /**
     * 商品下架
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/offGoods")
    public ModelAndView offGoods() throws Exception {

        return null;
    }

    /**
     * 用户删除商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteGoods/{id}")
    public String deleteGoods(HttpServletRequest request, @PathVariable("id") Integer id) throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        // 删除商品后，catlog的number-1，user表的goods_num-1，image删除,更新session的值
        User cur_user = (User) request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        int number = cur_user.getGoodsNum();
        Integer calelog_id = goods.getCatelogId();
        Catelog catelog = catelogService.selectByPrimaryKey(calelog_id);
        catelogService.updateCatelogNum(calelog_id, catelog.getNumber() - 1);
        userService.updateGoodsNum(cur_user.getId(), number - 1);
        cur_user.setGoodsNum(number - 1);
        request.getSession().setAttribute("cur_user", cur_user);// 修改session值
        //imageService.deleteImagesByGoodsPrimaryKey(id);
        goodsService.deleteGoodsByPrimaryKey(id);
        return "redirect:/user/allGoods";
    }

    /**
     * 根据商品id查询该商品详细信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/buyId/{id}")
    public ModelAndView getGoodsdetailById(HttpServletRequest request, @PathVariable("id") Integer id)
            throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        GoodsExtend goodsExtend = new GoodsExtend();
        List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(id);
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        Integer userId = cur_user.getId();
        Purse myPurse=purseService.getPurseByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtend", goodsExtend);
        modelAndView.addObject("myPurse",myPurse);
        modelAndView.setViewName("/user/pay");
        return modelAndView;
    }


    @RequestMapping("/test")
    public ModelAndView testPhoto(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/goods/testPhoto");
        return modelAndView;
    }
}
