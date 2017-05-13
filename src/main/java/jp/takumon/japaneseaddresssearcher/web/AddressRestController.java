package jp.takumon.japaneseaddresssearcher.web;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.Section;
import jp.takumon.japaneseaddresssearcher.domain.State;
import jp.takumon.japaneseaddresssearcher.service.AddressService;

/**
 * 住所検索用のRESTコントローラー
 *
 * @author takumon
 */
@RestController
@RequestMapping(value = "/api/v1/jp")
@Validated
public class AddressRestController {


  @Autowired
  private AddressService addressService;


  @ApiOperation(value = "都道府県情報一覧取得", notes = "全ての都道府県の一覧を取得します。")
  @RequestMapping(value = "/states", method = RequestMethod.GET)
  public List<State> getStates() {

    return addressService.getStates();
  }

  @ApiOperation(value = "都道府県情報取得", notes = "指定された都道府県名に紐づく住所の都道府県情報を取得します。")
  @RequestMapping(value = "/states/{stateName}", method = RequestMethod.GET)
  public State getState(
      @ApiParam(value = "都道府県名", required=true)
      @NotBlank
      @PathVariable(value = "stateName")
      final String stateName) {

    return addressService.getState(stateName);
  }

  @ApiOperation(value = "市区町村情報一覧取得", notes = "指定された都道府県名に紐づく住所の市区町村情報の一覧を取得します。")
  @RequestMapping(value = "/states/{stateName}/cities", method = RequestMethod.GET)
  public List<City> getCities(
      @ApiParam(value = "都道府県名", required=true)
      @NotBlank
      @PathVariable(value = "stateName")
      final String stateName) {

    return addressService.getCities(stateName);
  }


  @ApiOperation(value = "市区町村情報取得", notes = "指定された都道府県名と市区町村名に紐づく住所の市区町村情報を取得します。")
  @RequestMapping(value = "/states/{stateName}/cities/{cityName}", method = RequestMethod.GET)
  public City getCity(
      @ApiParam(value = "都道府県名", required=true)
      @NotBlank
      @PathVariable(value = "stateName")
      final String stateName,

      @ApiParam(value = "市区町村名", required=true)
      @NotBlank
      @PathVariable(value = "cityName")
      final String cityName) {

    return addressService.getCity(stateName, cityName);
  }


  @ApiOperation(value = "町域情報一覧取得", notes = "指定された都道府県名と市区町村名に紐づく住所の町域情報の一覧を取得します。")
  @RequestMapping(value = "/states/{stateName}/cities/{cityName}/sections",
      method = RequestMethod.GET)
  public List<Section> getSections(
      @ApiParam(value = "都道府県名", required=true)
      @NotBlank
      @PathVariable(value = "stateName")
      final String stateName,

      @ApiParam(value = "市区町村名", required=true)
      @NotBlank
      @PathVariable(value = "cityName")
      final String cityName) {

    return addressService.getSections(stateName, cityName);
  }


  @ApiOperation(value = "町域情報取得", notes = "指定された都道府県名と市区町村名と町域名に紐づく住所の町域情報を取得します。")
  @RequestMapping(value = "/states/{stateName}/cities/{cityName}/sections/{sectionName}",
      method = RequestMethod.GET)
  public Section getSection(
      @ApiParam(value = "都道府県名", required=true)
      @PathVariable(value = "stateName")
      final String stateName,

      @ApiParam(value = "市区町村名", required=true)
      @PathVariable(value = "cityName")
      final String cityName,

      @ApiParam(value = "町域名", required=true)
      @PathVariable(value = "sectionName")
      final String sectionName) {

    return addressService.getSection(stateName, cityName, sectionName);
  }

  @ApiOperation(value = "住所情報取得", notes = "指定された都道府県名と市区町村名と町域名に紐づく住所の一覧を取得します。")
  @RequestMapping(value = "/states/{stateName}/cities/{cityName}/sections/{sectionName}/addresses",
      method = RequestMethod.GET)
  public List<Address> getAddress(
      @ApiParam(value = "都道府県名", required=true)
      @PathVariable(value = "stateName")
      final String stateName,

      @ApiParam(value = "市区町村名", required=true)
      @PathVariable(value = "cityName")
      final String cityName,

      @ApiParam(value = "町域名", required=true)
      @PathVariable(value = "sectionName")
      final String sectionName) {

    return addressService.getAddress(stateName, cityName, sectionName);
  }

  @ApiOperation(value = "郵便番号検索", notes = "指定された郵便番号に紐づく住所の一覧を取得します。")
  @RequestMapping(value = "/addresses/{addressZipCode}", method = RequestMethod.GET)
  public List<Address> getAddress(
      @ApiParam(value = "郵便番号(000-0000)", required=true)
      @Pattern(regexp = "^\\d{3}-\\d{4}", message = "郵便番号が999-9999の形式ではありません。")
      @PathVariable(value = "addressZipCode")
      final String addressZipCode) {

    return addressService.getAddress(addressZipCode);
  }


  @ApiOperation(value = "キーワード検索", notes = "都道府県名、市区町村名、町域名のいずれかが指定されたキーワードに部分一致する住所の一覧を取得します。"
      + "住所情報のプロパティ名を指定する。 複数指定する場合はカンマ区切り。デフォルトは昇順で、プロパティ名の接頭辞に-をつけると降順になる。")
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public List<Address> search(
      @ApiParam(value = "キーワード", required=true)
      @NotBlank
      @RequestParam(value = "q")
      final String keyword,

      @ApiParam(value = "検索開始位置", defaultValue="${search.offset}")
      @RequestParam(value = "offset", defaultValue = "${search.offset}")
      final int offset,

      @ApiParam(value = "検索上限件数", defaultValue="${search.limit}")
      @RequestParam(value = "limit", defaultValue = "${search.limit}")
      final int limit,

      @ApiParam(value = "検索ソート順")
      @RequestParam(value = "sort")
      final String sort) {

    return addressService.searchAddresses(keyword, sort, offset, limit);
  }
}
