select
    section_id,
    section_name,
    section_kana,
    city_id,
    city_name,
    city_kana,
    state_id,
    state_name,
    state_kana,
    count(*) as address_count
from address
where
  state_name = /* stateName */''
  and city_name = /* cityName */''
  and section_name = /* sectionName */''
group by section_id;