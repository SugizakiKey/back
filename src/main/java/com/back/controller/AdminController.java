package com.back.controller;




import com.baomidou.mybatisplus.extension.api.ApiController;
import com.back.entity.*;
import com.back.response.Result;
import com.back.service.*;
import com.back.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2022-10-08 20:17:49
 */
@Slf4j
@RestController
@RequestMapping("/admin")

public class AdminController extends ApiController {

    @Resource
    private AdminService adminService;
    @Resource
    private AdminRolesService adminRolesService;
    @Resource
    private UserSellInfoService userSellInfoService;
    @Resource
    private BuycluesService buycluesService;
    @Resource
    private BuycluesFollowService buycluesFollowService;
    @Resource
    private CarAssessmentService carAssessmentService;
    @Resource
    private CarPictureService carPictureService;
    @Resource
    private CertificatePictureService certificatePictureService;
    @Resource
    private CarInventoryService carInventoryService;
    @Resource
    private CustomerService customerService;
    @Resource
    private SellscheduleService sellscheduleService;
    @Resource
    private SelloutService selloutService;
    @Resource
    private TransferService transferService;
    @Resource
    private IncidentalsService incidentalsService;
    @Resource
    private EquipService equipService;
    @Resource
    private UserBuyInfoService userBuyInfoService;
    @Resource
    private SellcluesService sellcluesService;
    @Resource
    private SellcluesFollowService sellcluesFollowService;
    @Resource
    private ReturncarService returncarService;
    @Resource
    private CarCollectionService carCollectionService;
    @Resource
    private CarPayService carPayService;
    @Resource
    private PermissionsService permissionsService;
    @Resource
    private RolesPermissionsService rolesPermissionsService;
    @Resource
    private NewsService newsService;
    @Resource
    private BrandService brandService;


    @GetMapping("testConnect")
    public  Result testConnect(@RequestParam Integer index){
        System.out.println("continue");
        return ResultUtil.success();
    }

    @PostMapping("checkAccount")
    public Result checkAccount(@RequestBody Admin admin){
        //@RequestParam(name = "account")String account, @RequestParam(name = "password")String password
        System.out.println(admin);
        return ResultUtil.success(adminService.checkAccount(admin));
    }
    @PostMapping("register")
    public Result register(@RequestBody Admin admin){
        return ResultUtil.success(adminService.register(admin));
    }
    @DeleteMapping("deleteById")
    public Result deleteById(@RequestParam Integer id){
        adminService.deleteAdminById(id);
        return ResultUtil.success();
    }
    @PutMapping("resetPassword")
    public Result resetPassword(@RequestParam Integer id){
        adminService.resetPassword(id);
        return ResultUtil.success();
    }
    @PostMapping("updatePassword")
    public Result updatePassword(@RequestBody Admin admin){
        return ResultUtil.success(adminService.updatePassword(admin));
    }
    @PostMapping("updateInfo")
    public Result uploader(@RequestParam( required = false) MultipartFile imgFile,String account,String name,String phone,
                           @RequestParam(required = false)String sex,@RequestParam(required = false)String email){
        return ResultUtil.success(adminService.updateInfo(imgFile,account,name,phone,sex,email));
    }
    @GetMapping("getAllAccount")
    public Result getAllAccount(){
        return ResultUtil.success1(adminService.getAllAccount());
    }
    @GetMapping("getAllAdminInfo")
    public Result getAllAdminInfo(){
        return ResultUtil.success1(adminService.getAllAdminInfo());
    }
    @GetMapping("getAdminById")
    public Result getAdminById(@RequestParam Integer id){
        return ResultUtil.success1(adminService.getAdminById(id));
    }

    @PostMapping("getAllAdminRoles")
    public Result getAllAdminRoles(@RequestParam String accountList[]){
        return ResultUtil.success1(adminRolesService.getRolesByAccountList(accountList));
    }

    @GetMapping("getIndexInfo")
    public Result getIndexInfo(){
        return ResultUtil.success1(carAssessmentService.getIndexInfo());
    }

    @GetMapping("getHeadInfo")
    public Result getHeadInfo(@RequestParam String roleName){
        return ResultUtil.success1(rolesPermissionsService.getHeadInfo(roleName));
    }


    //采购管理
    //除了未接和已接的
    @GetMapping("management/BuyManagement/getAllUserSellInfo")
    public Result getAllUserSellInfo(){
        return ResultUtil.success1(userSellInfoService.getAllUserSellInfo());
    }
    //未接的
    @GetMapping("management/BuyManagement/getNoUserSellInfo")
    public Result getNoUserSellInfo(){
        return ResultUtil.success1(userSellInfoService.getNoUserSellInfo());
    }
    //已接的
    @GetMapping("management/BuyManagement/getDeleteUserSellInfo")
    public Result getDeleteUserInfo(){
        return ResultUtil.success1(userSellInfoService.getDeleteUserSellInfo());
    }
    //拨打后设为未接
    @PostMapping("management/BuyManagement/AllToNo")
    public Result AllToNo(@RequestParam Integer id){
        userSellInfoService.AllToNo(id);
        return ResultUtil.success();
    }
    //已接
    @PostMapping("management/BuyManagement/AllToDelete")
    public Result AllToDelete(@RequestParam Integer id){
        userSellInfoService.AllToDelete(id);
        return ResultUtil.success();
    }
    @PostMapping("management/BuyManagement/NoToNo")
    public Result NoToNo(@RequestParam Integer id){
        return ResultUtil.success(userSellInfoService.NoToNo(id));
    }
    @DeleteMapping("management/BuyManagement/deleteOne")
    public Result deleteOne(@RequestParam Integer id){
        userSellInfoService.deleteOne(id);
        return ResultUtil.success();
    }
    @PostMapping("management/BuyManagement/deleteByIds")
    public Result deleteByIds(@RequestParam Integer ids[]){
        userSellInfoService.deleteByIds(ids);
        return ResultUtil.success();
    }


    @PostMapping("management/BuyManagement/saveBuyClues")
    public Result saveBuyClues(@RequestBody Buyclues buyclues){
        return ResultUtil.success(buycluesService.saveBuyClues(buyclues));
    }
    @GetMapping("management/BuyManagement/getBuyClues")
    public Result getBuyClues(@RequestParam char flag){
        return ResultUtil.success1(buycluesService.getBuyClues(flag));
    }
    @DeleteMapping("management/BuyManagement/deleteBuyClues")
    public Result deleteBuyClues(@RequestParam Integer id){
        return ResultUtil.success(buycluesService.deleteBuyClues(id));
    }
    @PutMapping("management/BuyManagement/updateBuyClues")
    public Result updateBuyClues(@RequestBody Buyclues buyclues){
        buycluesService.updateById(buyclues);
        return ResultUtil.success();
    }
    @PutMapping("management/BuyManagement/setState")
    public Result setState(@RequestParam String phone,@RequestParam String state){
        return ResultUtil.success(buycluesService.setState(phone,state));
    }
    @GetMapping("management/BuyManagement/getOne")
    public Result getOne(@RequestParam Integer id){
        return ResultUtil.success1(buycluesService.getOne(id));
    }


    @PostMapping("management/BuyManagement/saveBuyCluesFollow")
    public Result saveBuyCluesFollow(@RequestBody BuycluesFollow buycluesFollow){
        buycluesFollowService.saveBuyCluesFollow(buycluesFollow);
        return ResultUtil.success();
    }
    @GetMapping("management/BuyManagement/getFollowsByPhone")
    public Result getFollowsByPhone(@RequestParam String phone){
        return ResultUtil.success1(buycluesFollowService.getFollowsByPhone(phone));
    }


    @GetMapping("management/BuyManagement/getInventoryid")
    public Result getInventoryid(){
        return ResultUtil.success1(carAssessmentService.getInventoryid());
    }
    @PostMapping("management/BuyManagement/saveCarAssessment")
    public Result saveCarAssessment(@RequestBody CarAssessment carAssessment){
        return ResultUtil.success(carAssessmentService.saveCarAssessment(carAssessment));
    }
    @GetMapping("management/BuyManagement/getAllCarAssessment")
    public Result getAllCarAssessment(){
        return ResultUtil.success1(carAssessmentService.getAllCarAssessment());
    }
    @PostMapping("management/BuyManagement/setCarurl")
    public Result setCarurl(@RequestParam String inventoryid){
        log.info("set");
        carAssessmentService.setCarurl(inventoryid);
        return ResultUtil.success();
    }
    @GetMapping("management/BuyManagement/getCarAssessmentById")
    public Result getCarAssessmentById(@RequestParam String inventoryid){
        return ResultUtil.success1(carAssessmentService.getCarAssessmentById(inventoryid));
    }
    @PostMapping("management/BuyManagement/updateCarAssessment")
    public Result updateCarAssessment(@RequestBody CarAssessment assessment){
        carAssessmentService.updateById(assessment);
        return ResultUtil.success();
    }
    @PostMapping("management/BuyManagement/setAssessmentState")
    public Result setAssessmentState(@RequestParam String inventoryid){
        return ResultUtil.success(carAssessmentService.setAssessmentState(inventoryid));
    }
    @DeleteMapping("management/BuyManagement/deleteCarAssessmentById")
    public Result deleteCarAssessmentById(@RequestParam String inventoryid){
        return ResultUtil.success(carAssessmentService.deleteCarAssessmentById(inventoryid));
    }
    @PostMapping("management/BuyManagement/getCarAssessmentByIds")
    public Result getCarAssessmentByIds(@RequestParam String inventoryid[]){
        return ResultUtil.success1(carAssessmentService.getCarAssessmentByIds(inventoryid));
    }


    @PostMapping("management/BuyManagement/saveCarPicture")
    public Result saveCarPicture(@RequestParam String inventoryid,@RequestParam(required = false) MultipartFile files[],
                                 @RequestParam(required = false) String firsturl){
        carPictureService.saveCarPicture(inventoryid,files,firsturl);
        return ResultUtil.success();
    }
    @GetMapping("management/BuyManagement/getCarPictureById")
    public Result getCarPictureById(@RequestParam String inventoryid){
        return ResultUtil.success1(carPictureService.getCarPictureById(inventoryid));
    }
    @PostMapping("management/BuyManagement/deleteCarPictureByIds")
    public Result deleteCarPictureByIds(@RequestParam Integer ids[]){
        carPictureService.deleteCarPictureByIds(ids);
        return ResultUtil.success();
    }
    @DeleteMapping("management/BuyManagement/deleteCarpictureByInventoryid")
    public Result deleteCarpictureByInventoryid(@RequestParam String inventoryid){
        carPictureService.deleteAllCarPictureByInventoryid(inventoryid);
        return ResultUtil.success();
    }


    @PostMapping("management/BuyManagement/saveCetificatePictures")
    public Result saveCetificatePictures(@RequestParam String inventoryid,@RequestParam(required = false) String names[],
                                         @RequestParam(required = false) MultipartFile files[],@RequestParam(required = false)Boolean states[]){
        certificatePictureService.saveCetificatePictures(inventoryid,names,files,states);
        return ResultUtil.success();
    }
    @GetMapping("management/BuyManagement/getCetificatePictures")
    public Result getCetificatePictures(@RequestParam String inventoryid){
        return ResultUtil.success1(certificatePictureService.getCetificatePicturesById(inventoryid));
    }


    @PostMapping("management/BuyManagement/saveCarInventory")
    public Result saveCarInventory(@RequestBody CarInventory carInventory){
        return ResultUtil.success(carInventoryService.saveCarInventory(carInventory));
    }
    @GetMapping("management/BuyManagement/getAllCarInventory")
    public Result getAllInventory(){
        return ResultUtil.success1(carInventoryService.getAllCarInventory());
    }
    @PostMapping("management/BuyManagement/setIsPass")
    public Result setIsPass(@RequestParam String inventoryid,@RequestParam String flag){
        carInventoryService.setIsPass(inventoryid,flag);
        return ResultUtil.success();
    }
    @DeleteMapping("management/BuyManagement/realDeleteCarInventoryById")
    public Result realDeleteCarInventoryById(@RequestParam Integer id){
        return ResultUtil.success(carInventoryService.deleteRealCarInventoryById(id));
    }


    @GetMapping("management/BuyManagement/getAllBrandInfo")
    public Result getAllBrandInfo(){
        return ResultUtil.success1(brandService.getAllBrandInfo());
    }




    //库存管理
    @GetMapping("management/InventoryManagement/getAllCarInventoryIspass")
    public Result getAllCarInventoryIspass(){
        return ResultUtil.success1(carInventoryService.getAllCarInventoryIsPass());
    }
    @PostMapping("management/InventoryManagement/updateCarInventoryState")
    public Result updateCarInventoryState(@RequestBody CarInventory carInventory){
        carInventoryService.updateCarInventoryState(carInventory);
        return ResultUtil.success();
    }
    @PostMapping("management/InventoryManagement/updateCarInventoryPrice")
    public Result updateCarInventoryPrice(@RequestBody CarInventory carInventory){
        carInventoryService.updateCarInventoryPrice(carInventory);
        return ResultUtil.success();
    }
    @GetMapping("management/InventoryManagement/getRealCarInventoryById")
    public Result getRealCarInventoryById(@RequestParam Integer id){
        return ResultUtil.success1(carInventoryService.getRealCarInventoryById(id));
    }
    @PutMapping("management/InventoryManagement/updateCarStand")
    public Result updateCarStand(@RequestBody CarInventory carInventory){
        carInventoryService.updateCarStand(carInventory);
        return ResultUtil.success();
    }




    @PostMapping("management/InventoryManagement/saveSellschedule")
    public Result saveSellschedule(@RequestBody Sellschedule sellschedule){
        return ResultUtil.success(sellscheduleService.saveSellschedule(sellschedule));
    }
    @GetMapping("management/InventoryManagement/getSellscheduleDate")
    public Result getSellscheduleDate(@RequestParam String inventoryid){
        return ResultUtil.success1(sellscheduleService.getSellscheduleDate(inventoryid));
    }

    @PostMapping("management/InventoryManagement/saveSellout")
    public Result saveSellout(@RequestBody Sellout sellout){
        return ResultUtil.success(selloutService.saveSellOut(sellout));
    }

    @PostMapping("management/InventoryManagement/saveTransfer")
    public Result saveTransfer(@RequestBody Transfer transfer){
        return ResultUtil.success(transferService.saveTransfer(transfer));
    }
    @GetMapping("management/InventoryManagement/getTransferByIdType")
    public Result getTransferByIdType(@RequestParam String inventoryid,@RequestParam String type){
        return ResultUtil.success(transferService.getTransferByIdType(inventoryid,type));
    }
    @GetMapping("management/InventoryManagement/getAllTransferByType")
    public Result getAllTransferByType(@RequestParam String type){
        return ResultUtil.success1(transferService.getAllTransferByType(type));
    }
    @PutMapping("management/InventoryManagement/setTransferByIdNameType")
    public Result setTransferByIdNameType(@RequestParam Integer id,@RequestParam String name,@RequestParam String type){
        transferService.setTransferByIdNameType(id,name,type);
        return ResultUtil.success();
    }
    @GetMapping("management/InventoryManagement/getTransferById")
    public Result getTransferById(@RequestParam Integer id){
        return ResultUtil.success1(transferService.getTransferById(id));
    }
    @DeleteMapping("management/InventoryManagement/deleteTransferById")
    public Result deleteTransferById(@RequestParam Integer id){
        return ResultUtil.success(transferService.deleteTransferById(id));
    }


    @PostMapping("management/InventoryManagement/saveIncidentals")
    public Result saveIncidentals(@RequestParam String inventoryid,@RequestParam String branddept,@RequestParam String personinchargeaccount,
                                  @RequestParam Integer rows,@RequestParam String[] costitems,@RequestParam Double[] amounts,
                                  @RequestParam String[] types,@RequestParam String[] remarks,@RequestParam String[] dates){
        incidentalsService.saveIncidentals(inventoryid,branddept,personinchargeaccount,rows,costitems,amounts,types,remarks,dates);
        return ResultUtil.success();
    }


    @PostMapping("management/InventoryManagement/saveEquip")
    public Result saveEquip(@RequestParam String inventoryid, @RequestParam String branddept,@RequestParam String carstand,
                            @RequestParam Integer rows,@RequestParam String[] costitems,@RequestParam Double[] amounts,
                            @RequestParam String[] remarks,@RequestParam String[] startdates,@RequestParam String[] enddates,
                            @RequestParam String[] types,@RequestParam String adminaccount){
        equipService.saveEquip(inventoryid,branddept,carstand,rows,costitems,amounts,remarks,startdates,enddates,types,adminaccount);
        return ResultUtil.success();
    }
    @PostMapping("management/InventoryManagement/getEquipByInventoryids")
    public Result getEquipByInventoryids(@RequestParam String inventoryid[]){
        return ResultUtil.success1(equipService.getEquipByInventoryids(inventoryid));
    }
    @GetMapping("management/InventoryManagement/getEquipById")
    public Result getEquipById(@RequestParam Integer id){
        return ResultUtil.success1(equipService.getEquipById(id));
    }
    @PutMapping("management/InventoryManagement/updateEquip")
    public Result updateEquip(@RequestBody Equip equip){
        equipService.updateEquip(equip);
        return ResultUtil.success();
    }
    @DeleteMapping("management/InventoryManagement/deleteEquipById")
    public Result deleteEquipById(@RequestParam Integer id){
        equipService.removeById(id);
        return ResultUtil.success();
    }






    //销售管理
    @GetMapping("management/SellManagement/getCarInventoryIn")
    public Result getCarInventoryIn(){
        return ResultUtil.success1(carInventoryService.getCarInventoryIn());
    }
    //除了未接和已接的
    @GetMapping("management/SellManagement/getAllUserBuyInfo")
    public Result getAllUserBuyInfo(){
        return ResultUtil.success1(userBuyInfoService.getAllUserBuyInfo());
    }
    //未接的
    @GetMapping("management/SellManagement/getNoUserBuyInfo")
    public Result getNoUserBuyInfo(){
        return ResultUtil.success1(userBuyInfoService.getNoUserBuyInfo());
    }
    //已接的
    @GetMapping("management/SellManagement/getDeleteUserBuyInfo")
    public Result getDeleteUserBuyInfo(){
        return ResultUtil.success1(userBuyInfoService.getDeleteUserBuyInfo());
    }
    //拨打后设为未接
    @PostMapping("management/SellManagement/AllToNo")
    public Result AllToNo1(@RequestParam Integer id){
        userBuyInfoService.AllToNo(id);
        return ResultUtil.success();
    }
    //已接
    @PostMapping("management/SellManagement/AllToDelete")
    public Result AllToDelete1(@RequestParam Integer id){
        userBuyInfoService.AllToDelete(id);
        return ResultUtil.success();
    }
    @PostMapping("management/SellManagement/NoToNo")
    public Result NoToNo1(@RequestParam Integer id){
        return ResultUtil.success(userBuyInfoService.NoToNo(id));
    }
    @DeleteMapping("management/SellManagement/deleteOne")
    public Result deleteOne1(@RequestParam Integer id){
        userBuyInfoService.deleteOne(id);
        return ResultUtil.success();
    }
    @PostMapping("management/SellManagement/deleteByIds")
    public Result deleteByIds1(@RequestParam Integer ids[]){
        userBuyInfoService.deleteByIds(ids);
        return ResultUtil.success();
    }


    @PostMapping("management/SellManagement/saveSellClues")
    public Result saveSellClues(@RequestBody Sellclues sellclues){
        return ResultUtil.success(sellcluesService.saveSellClues(sellclues));
    }
    @GetMapping("management/SellManagement/getSellClues")
    public Result getSellClues(@RequestParam char flag){
        return ResultUtil.success1(sellcluesService.getSellClues(flag));
    }
    @DeleteMapping("management/SellManagement/deleteSellClues")
    public Result deleteSellClues(@RequestParam Integer id){
        return ResultUtil.success(sellcluesService.deleteSellClues(id));
    }
    @PutMapping("management/SellManagement/updateSellClues")
    public Result updateSellClues(@RequestBody Sellclues sellclues){
        sellcluesService.updateById(sellclues);
        return ResultUtil.success();
    }
    @PutMapping("management/SellManagement/setState")
    public Result setState1(@RequestParam String phone,@RequestParam String state){
        return ResultUtil.success(sellcluesService.setState(phone,state));
    }
    @GetMapping("management/SellManagement/getOne")
    public Result getOne1(@RequestParam Integer id){
        return ResultUtil.success1(sellcluesService.getOne(id));
    }


    @PostMapping("management/SellManagement/saveSellCluesFollow")
    public Result saveSellCluesFollow(@RequestBody SellcluesFollow sellcluesFollow){
        sellcluesFollowService.saveSellCluesFollow(sellcluesFollow);
        return ResultUtil.success();
    }
    @GetMapping("management/SellManagement/getFollowsByPhone")
    public Result getFollowsByPhone1(@RequestParam String phone){
        return ResultUtil.success1(sellcluesFollowService.getFollowsByPhone(phone));
    }

    @GetMapping("management/SellManagement/getAllSellOut")
    public Result getAllSellOut(){
        return ResultUtil.success1(selloutService.getAllSellOut());
    }
    @DeleteMapping("management/SellManagement/setIsPass")
    public Result setIsPass(@RequestParam Integer id,@RequestParam Boolean flag){
        selloutService.setIsPass(id,flag);
        return ResultUtil.success();
    }
    @GetMapping("management/SellManagement/getPassSellOut")
    public Result getPassSellOut(){
        return ResultUtil.success1(selloutService.getPassSellOut());
    }
    @GetMapping("management/SellManagement/getSellOutById")
    public Result getSellOutById(@RequestParam Integer id){
        return ResultUtil.success1(selloutService.getSellOutById(id));
    }


    @PostMapping("management/SellManagement/getCustomerByPhones")
    public Result getCustomerByPhones(@RequestParam String phones[]){
        return ResultUtil.success1(customerService.getCustomerByPhones(phones));
    }


    @PostMapping("management/SellManagement/saveReturnCar")
    public Result saveReturnCar(@RequestBody Returncar returncar){
        returncarService.saveReturnCar(returncar);
        return ResultUtil.success();
    }

    @PostMapping("management/SellManagement/getCarCollectionByInventoryid")
    public Result getCarCollectionByInventoryid(@RequestParam String inventoryid[]){
        return ResultUtil.success1(carCollectionService.getCarCollectionByInventoryid(inventoryid));
    }
    @PostMapping("management/SellManagement/saveCarCollection")
    public Result saveCarCollection(@RequestBody CarCollection carCollection){
        carCollectionService.save(carCollection);
        return ResultUtil.success();
    }
    @GetMapping("management/SellManagement/getCarCollection")
    public Result getCarCollection(@RequestParam String inventoryid){
        return ResultUtil.success1(carCollectionService.getCarCollection(inventoryid));
    }
    @PostMapping("management/SellManagement/deleteCarCollection")
    public Result deleteCarCollection(@RequestBody CarCollection carCollection){
        carCollectionService.deleteCarCollection(carCollection);
        return ResultUtil.success();
    }
    @DeleteMapping("management/SellManagement/RealDeleteById")
    public Result RealDeleteById(@RequestParam Integer id){
        selloutService.RealDeleteById(id);
        return ResultUtil.success();
    }










    //客户管理
    @GetMapping("management/CustomerManagement/getCustomerid")
    public Result getCustomerid(){
        return ResultUtil.success1(customerService.getCustomerid());
    }
    @PostMapping("management/CustomerManagement/saveCustomer")
    public Result saveCustomer(@RequestBody Customer customer){
        return ResultUtil.success(customerService.saveCustomer(customer));
    }
    @GetMapping("management/CustomerManagement/getAllCustomer")
    public Result getAllCustomer(){
        return ResultUtil.success1(customerService.getAllCustomer());
    }
    @GetMapping("management/CustomerManagement/getCustomerById")
    public Result getCustomerById(@RequestParam Integer id){
        return ResultUtil.success1(customerService.getCustomerById(id));
    }
    @PutMapping("management/CustomerManagement/updateCustomer")
    public Result updateCustomer(@RequestBody Customer customer){
        return ResultUtil.success(customerService.updateCustomer(customer));
    }
    @DeleteMapping("management/CustomerManagement/setFail")
    public Result setFail(@RequestParam Integer id){
        return ResultUtil.success(customerService.setFail(id));
    }
    @DeleteMapping("management/CustomerManagement/realDeleteById")
    public Result realDeleteById(@RequestParam Integer id){
        return ResultUtil.success(customerService.realDeleteById(id));
    }
    @GetMapping("management/CustomerManagement/getFailCustomer")
    public Result getFailCustomer(){
        return ResultUtil.success1(customerService.getFailCustomer());
    }
    @PutMapping("management/CustomerManagement/setActive")
    public Result setActive(@RequestParam Integer id){
        return ResultUtil.success(customerService.setActive(id));
    }
    @GetMapping("management/CustomerManagement/getSuccessCustomer")
    public Result getSuccessCustomer(){
        return ResultUtil.success1(customerService.getSuccessCustomer());
    }
    @PostMapping("management/CustomerManagement/getCustomerCars")
    public Result getCustomerCars(@RequestParam String customerids[]){
        return ResultUtil.success1(customerService.getCustomerCars(customerids));
    }





    //财务费用
    @GetMapping("management/FinancialExpenses/getCarPays")
    public Result getCarPays(){
        return ResultUtil.success1(carPayService.getCarPays());
    }
    @PostMapping("operation/FinancialExpenses/getCarPayByInventoryid")
    public Result getCarPayByInventoryid(@RequestParam String inventoryid[]){
        return ResultUtil.success1(carPayService.getCarPayByInventoryid(inventoryid));
    }
    @PostMapping("management/FinancialExpenses/saveCarPay")
    public Result saveCarPay(@RequestBody CarPay carPay){
        carPayService.save(carPay);
        return ResultUtil.success();
    }


    @GetMapping("management/FinancialExpenses/getCarCollections")
    public Result getCarCollections(){
        return ResultUtil.success1(carCollectionService.getCarCollections());
    }


    @GetMapping("management/FinancialExpenses/getAllIncidentalsInfo")
    public Result getAllIncidentalsInfo(){
        return ResultUtil.success1(incidentalsService.getAllIncidentalsInfo());
    }

    @GetMapping("management/FinancialExpenses/getCarInventoryOut")
    public Result getCarInventoryOut(){
        return ResultUtil.success1(carInventoryService.getCarInventoryOut());
    }







    //系统管控
    @GetMapping("operation/SystemControl/getAllPermissions")
    public Result getAllPermissions(){
        return ResultUtil.success1(permissionsService.getAllPermissions());
    }


    @PostMapping("operation/SystemControl/saveRolesPermissions")
    public Result saveRolesPermissions(@RequestParam String roleName,@RequestParam Integer permissionids[]){
        return ResultUtil.success(rolesPermissionsService.saveRolesPermissions(roleName,permissionids));
    }
    @GetMapping("operation/SystemControl/getAllRoleNames")
    public Result getAllRoleNames(){
        return ResultUtil.success1(rolesPermissionsService.getAllRoleNames());
    }
    @GetMapping("operation/SystemControl/getPermissionsByRoleName")
    public Result getPermissionsByRoleName(@RequestParam String roleName){
        return ResultUtil.success1(rolesPermissionsService.getPermissionsByRoleName(roleName));
    }
    @PutMapping("operation/SystemControl/updateRolesPermissions")
    public Result updateRolesPermissions(@RequestParam String roleName,@RequestParam Integer permissionids[]){
        rolesPermissionsService.updateRolesPermissions(roleName,permissionids);
        return ResultUtil.success();
    }
    @DeleteMapping("operation/SystemControl/deleteRolesByRoleName")
    public Result deleteRolesByRoleName(@RequestParam String roleName){
        rolesPermissionsService.deleteRolesByRoleName(roleName);
        return ResultUtil.success();
    }


    @PostMapping("operation/SystemControl/addAdmin")
    public Result addAdmin(@RequestBody Admin admin,@RequestParam String roleName){
        return ResultUtil.success(adminService.addAdmin(admin,roleName));
    }
    @PutMapping("operation/SystemControl/updateAdmin")
    public Result updateAdmin(@RequestBody Admin admin,@RequestParam String roleName){
        adminService.updateAdmin(admin,roleName);
        return ResultUtil.success();
    }

    @GetMapping("operation/SystemControl/getRolesByAccount")
    public Result getRolesByAccount(@RequestParam String account){
        return ResultUtil.success1(adminRolesService.getRolesByAccount(account));
    }





    //营销交易
    @PostMapping("marketing/MarketingDeals/addNews")
    public Result addNews(@RequestBody News news){
        newsService.save(news);
        return ResultUtil.success();
    }
    @GetMapping("marketing/MarketingDeals/getAllNews")
    public Result getAllNews(){
        return ResultUtil.success1(newsService.getAllNews());
    }
    @GetMapping("marketing/MarketingDeals/getNewsById")
    public Result getNewsById(@RequestParam Integer id){
        return ResultUtil.success1(newsService.getNewsById(id));
    }
    @PutMapping("marketing/MarketingDeals/updateNews")
    public Result updateNews(@RequestBody News news){
        newsService.updateById(news);
        return ResultUtil.success();
    }
    @DeleteMapping("marketing/MarketingDeals/deleteNewsById")
    public Result deleteNewsById(@RequestParam Integer id){
        newsService.removeById(id);
        return ResultUtil.success();
    }
    @PostMapping("marketing/MarketingDeals/deleteNewsByIds")
    public Result deleteNewsByIds(@RequestParam Integer ids[]){
        newsService.deleteNewsByIds(ids);
        return ResultUtil.success();
    }











    //@RequiresPermissions("per1")
    @RequiresRoles("总经理")
    @GetMapping("test")
    public Result test(){
        log.info("进入test！！！");
        return new Result().setMessage("进来咯！！！！");
    }

    @GetMapping("test1")
    public Result test1(){
        log.info("进入test1！！！");
        return new Result().setMessage("进来咯！！！！");
    }


}

