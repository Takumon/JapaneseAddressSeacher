select
    city_id,
    city_name,
    city_kana,
    state_id,
    state_name,
    state_kana,
    count(*) as section_count
from address
where 
  state_name = /* stateName */''
  and city_name = /* cityName */''
group by city_id;