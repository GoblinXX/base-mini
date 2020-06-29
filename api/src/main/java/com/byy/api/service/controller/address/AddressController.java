package com.byy.api.service.controller.address;

import com.byy.api.response.ResponseObject;
import com.byy.api.service.controller.base.BaseController;
import com.byy.api.service.vo.address.AddressChainVO;
import com.byy.biz.service.address.AddressChainService;
import com.byy.biz.service.address.AreaService;
import com.byy.biz.service.address.CityService;
import com.byy.biz.service.address.ProvinceService;
import com.byy.dal.common.utils.helper.Transformer;
import com.byy.dal.common.utils.provider.WrapperProvider;
import com.byy.dal.entity.beans.address.Area;
import com.byy.dal.entity.beans.address.City;
import com.byy.dal.entity.beans.address.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.byy.api.response.ResponseObject.success;

/**
 * 省市区固定地址
 *
 * @author: yyc
 * @date: 19-6-12 下午5:08
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

  private final ProvinceService provinceService;
  private final CityService cityService;
  private final AreaService areaService;
  private final AddressChainService addressChainService;

  @Autowired
  public AddressController(
      ProvinceService provinceService,
      CityService cityService,
      AreaService areaService,
      AddressChainService addressChainService) {
    this.provinceService = provinceService;
    this.cityService = cityService;
    this.areaService = areaService;
    this.addressChainService = addressChainService;
  }

  /**
   * 根据areaId查询省市区
   *
   * @param areaId Long
   * @return AddressChainVO
   */
  @GetMapping("/chain/{areaId}")
  public ResponseObject<AddressChainVO> getAddressChainByAreaId(@PathVariable Long areaId) {
    return success(
        Transformer.fromBean(addressChainService.loadAddressChain(areaId), AddressChainVO.class));
  }

  /**
   * 查询所有省
   *
   * @return ResponseObject
   */
  @GetMapping("/province")
  public ResponseObject<List<Province>> getProvinces() {
    List<Province> provinces = provinceService.list();
    return success(provinces);
  }

  /**
   * 查询省下面的市
   *
   * @param provinceId Long
   * @return ResponseObject
   */
  @GetMapping("/city/{provinceId}")
  public ResponseObject<List<City>> getCities(@PathVariable Long provinceId) {
    List<City> cities =
        cityService.list(WrapperProvider.queryWrapper(City::getProvinceId, provinceId));
    return success(cities);
  }

  /**
   * 查询市下面的区
   *
   * @param cityId Long
   * @return ResponseObject
   */
  @GetMapping("/area/{cityId}")
  public ResponseObject<List<Area>> getAreas(@PathVariable Long cityId) {
    List<Area> areas = areaService.list(WrapperProvider.queryWrapper(Area::getCityId, cityId));
    return success(areas);
  }
}
