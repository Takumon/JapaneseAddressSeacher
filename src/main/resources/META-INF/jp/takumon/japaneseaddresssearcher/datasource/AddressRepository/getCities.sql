select
    city_id,
    city_name,
    city_kana,
    state_id,
    state_name,
    state_kana,
    count(*) as city_section_count
from address
where state_id = /* stateId */40
group by city_id
order by city_id;