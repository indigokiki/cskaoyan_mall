package com.cskaoyan.controller.goods;

import com.cskaoyan.bean.goods.CskaoyanMallComment;
import com.cskaoyan.bean.goods.*;
import com.cskaoyan.service.goods.GoodsAttributeService;
import com.cskaoyan.service.goods.GoodsProductService;
import com.cskaoyan.service.goods.GoodsService;
import com.cskaoyan.service.goods.GoodsSpecificationService;
import com.cskaoyan.util.Page;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.goods.ValueAndLabel;
import com.cskaoyan.util.goods.ValueLabelAndChildren;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YangShuo
 * @date 2019-07-03 20:26
 * 负责商品管理模块：1.商品列表 2.商品上架 3.商品评论
 */
@RestController
@RequestMapping("/admin")
public class GoodsController {

    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAttributeService attributeService;
    @Autowired
    GoodsProductService productService;
    @Autowired
    GoodsSpecificationService specificationService;

    //一、商品列表

    //1./admin/goods/list?page=1&limit=20&sort=add_time&order=desc
    //2.1 按商品编号查找（精确）/goods/list?page=1&limit=20&goodsSn=12&sort=add_time&order=desc
    //2.2 按商品名称查找（模糊） /goods/list?page=1&limit=20&goodsSn=&name=1212&sort=add_time&order=desc
    @RequestMapping("/goods/list")
    public ResponseVo<Page> goodsList(int page, int limit, String goodsSn, String name, String sort, String order) {
        Page<CskaoyanMallGoods> goodsPage = goodsService.selectGoodsPageWithSnOrName(page, limit, goodsSn, name);
        ResponseVo<Page> pageResponseVo = new ResponseVo<>();
        pageResponseVo.setData(goodsPage);
        pageResponseVo.setErrno(0);
        pageResponseVo.setErrmsg("成功");

        return pageResponseVo;
    }

    //3./goods/delete  传递一个goods对象,不是真的删除，只是把deleted这列从0改成1
    //并且把关联的attribute、product、specification表对应goodsid的列的deleted都改为1
    @RequestMapping("/goods/delete")
    public ResponseVo goodsDelete(@RequestBody CskaoyanMallGoods goods) {
        int i = goodsService.changeGoodsAndRelatedDeleted(goods);

        ResponseVo<Object> responseVo = new ResponseVo<>();
        if (i == 1) {
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");

        } else {
            responseVo.setErrno(1);
            responseVo.setErrmsg("失败");
        }
        return responseVo;
    }


    /*4.商品编辑：使用goodsid查询结果，返回的data包括：
     *attributes
     *categoryids--父类目，子类目
     * goods
     * products
     * specifications
     * */
    @RequestMapping("/goods/detail")
    public ResponseVo goodsDetail(Integer id){
        GoodsDetail goodsDetail = goodsService.getGoodsDetailById(id);
        ResponseVo<GoodsDetail> responseVo = new ResponseVo<>();
        if (goodsDetail == null){
            responseVo.setErrno(502);
            responseVo.setErrmsg("系统内部错误");
        }else {
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            responseVo.setData(goodsDetail);
        }
        return responseVo;
    }

    //5.商品更新，类似上架
    @RequestMapping("/goods/update")
    public ResponseVo goodsUpdate(@RequestBody GoodsAdd goodsAdd){
        ResponseVo<Object> responseVo = new ResponseVo<>();

        CskaoyanMallGoods goods = goodsAdd.getGoods();
        List<CskaoyanMallGoodsAttribute> attributes = goodsAdd.getAttributes();
        List<CskaoyanMallGoodsProduct> products = goodsAdd.getProducts();
        List<CskaoyanMallGoodsSpecification> specifications = goodsAdd.getSpecifications();

        //因为是更新操作，比新增操作多了id
        int i = 0;
        i += goodsService.updateGoods(goods);
        i += attributeService.updateAttributes(attributes);
        i += productService.updateProducts(products);
        i += specificationService.updateSpecifications(specifications);
        if (i == 4){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
        }
        return responseVo;
    }



    //二、商品上架

    //1.进入页面/goods/create,但请求里没有
    /*请求里是通过http://localhost:9526/static/tinymce4.7.5/skins/lightgray/content.min.css获得上架页面的
    不用后端写*/


    //2./goods/catAndBrand，查brandList+categoryList,data里有两张表
    //brandList只查brand的value和label，categoryList第一次查pid=0的，第二次用pid查对应的
    @RequestMapping("/goods/catAndBrand")
    public ResponseVo goodsCatAndBrand() {
        List<ValueAndLabel> brandList = goodsService.getAllBrandsIdAndName();
        List<ValueLabelAndChildren> categoryList = goodsService.getAllCategorysIdAndName();

        Map<String, Object> catAndBrandMap = new HashMap<>();
        catAndBrandMap.put("brandList", brandList);
        catAndBrandMap.put("categoryList", categoryList);

        ResponseVo<Map<String, Object>> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(catAndBrandMap);
        return responseVo;

    }

    //3.商品图片上传，/storage/create
    //传递一个file(binary),存入storage表，并且返回新增的这个对象


    //4.商品上架 /goods/create，传递4种对象(每种传多个对象)：attributes,goods,products,specifications
    @RequestMapping("/goods/create")
    public ResponseVo goodsCreate(@RequestBody GoodsAdd goodsAdd){
        ResponseVo<Object> responseVo = new ResponseVo<>();

        CskaoyanMallGoods goods = goodsAdd.getGoods();
        List<CskaoyanMallGoodsAttribute> attributes = goodsAdd.getAttributes();
        List<CskaoyanMallGoodsProduct> products = goodsAdd.getProducts();
        List<CskaoyanMallGoodsSpecification> specifications = goodsAdd.getSpecifications();

        //4.1 处理传入的goods(pojo)
        if (goods != null){
            //如果商品名称已存在，则返回错误信息
            if (goodsService.goodsNameExist(goods.getName())){
                responseVo.setErrno(611);
                responseVo.setErrmsg("商品名称已存在");
                return responseVo;
            }
            //如果商品编号已存在，则返回错误信息
            else if (goodsService.goodsSnExist(goods.getGoodsSn())){
                responseVo.setErrno(610);
                responseVo.setErrmsg("商品编号已存在");
                return responseVo;
            }
            else {
                //若商品名称/编号不存在，则添加该商品，并返回它的id给后续使用。
                Integer goodsId = goodsService.getGoodsidWhenInsertGoods(goods);

                //4.2 处理传入的attributes(list)
                if (attributes != null) {
                    for (CskaoyanMallGoodsAttribute attribute : attributes) {
                        attribute.setGoodsId(goodsId);
                        attributeService.insertAttributeSelective(attribute);
                    }
                }

                //4.3 处理传入的products(list)
                if (products != null) {
                    for (CskaoyanMallGoodsProduct product : products) {
                        product.setGoodsId(goodsId);
                        productService.insertProductSelective(product);
                    }
                }
                //4.4 处理传入的specifications(list)
                if (specifications != null) {
                    for (CskaoyanMallGoodsSpecification specification : specifications) {
                        specification.setGoodsId(goodsId);
                        specificationService.insertSpecificationSelective(specification);
                    }
                }

            }
        }
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");

        return responseVo;
    }



    //三、商品评论

    //1.页面展示 /comment/list?page=1&limit=20&sort=add_time&order=desc
    //2.1用户ID查找(精确) /comment/list?page=1&limit=20&userId=1&sort=add_time&order=desc
    //2.2 商品ID查找(精确) /comment/list?page=1&limit=20&userId=&valueId=102&sort=add_time&order=desc
    @RequestMapping("/comment/list")
    public ResponseVo<Page> commentList(int page, int limit, Integer userId, Integer valueId, String sort, String order) {
        Page<CskaoyanMallComment> commentPage = goodsService.selectCommentsPageWithSnOrName(page, limit, userId, valueId);
        ResponseVo<Page> pageResponseVo = new ResponseVo<>();
        pageResponseVo.setData(commentPage);
        pageResponseVo.setErrno(0);
        pageResponseVo.setErrmsg("成功");

        return pageResponseVo;
    }

    //3.商品评论的回复,传递json数据{commentId, content}
    @RequestMapping("/order/reply")
    public ResponseVo commengReply(@RequestBody CommentidAndContent commentidAndContent){
        ResponseVo<Object> responseVo = new ResponseVo<>();

        Integer commentId = commentidAndContent.getCommentId();
        //判断对应行的content是否已存在，已存在就返回错误信息
        if (goodsService.commentContentExist(commentId)){
            responseVo.setErrno(622);
            responseVo.setErrmsg("订单商品已回复");
            return responseVo;
        }
        //若content不存在，则用commentid存入content
        String content = commentidAndContent.getContent();
        int i = goodsService.updateContentOfComment(commentId, content);
        if (i == 1){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
        }else {
            responseVo.setErrno(621);
            responseVo.setErrmsg("订单商品回复添加失败");
        }

        return responseVo;

    }

    //4.商品删除
    @RequestMapping("/comment/delete")
    public ResponseVo commentDelete(@RequestBody CskaoyanMallComment comment){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        int i = goodsService.changeCommentDeleted(comment);
        if (i == 1){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
        }else {
            responseVo.setErrno(620);
            responseVo.setErrmsg("删除失败");
        }
        return responseVo;
    }





}
