select
    address_zip_code,
    section_id,
    section_name,
    section_kana,
    city_id,
    city_name,
    city_kana,
    state_id,
    state_name,
    state_kana
from address
where
  state_name = /* stateName */''
  city_name = /* cityName */'';
order by section_id
