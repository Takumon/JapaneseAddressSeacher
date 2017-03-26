select
  id,
  state_id,
  city_id,
  section_id,
  address_zip_code,
  office_flg,
  delete_flg,
  state_name,
  state_kana,
  city_name,
  city_kana,
  section_name,
  section_kana,
  section_memo,
  kyoto_street,
  block_name,
  block_kana,
  memo,
  office_name,
  office_kana,
  office_address,
  new_id
from address
where 
    state_name like /* @infix(keyword) */'%X%'
    or city_name like /* @infix(keyword) */'%X%'
    or section_name  like /* @infix(keyword) */'%X%'
/*# orderBy */
;