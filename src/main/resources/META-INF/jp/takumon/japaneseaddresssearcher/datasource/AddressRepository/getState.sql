select
    tb1.state_id as state_id,
    tb1.state_name as state_name,
    tb1.state_kana as state_kana,
    count(*) as city_count
from
    (
        select state_id, state_name, state_kana
        from address
        group by state_id, state_name, state_kana, city_name
    )tb1
where
  state_name = /* stateName */''
group by tb1.state_name
order by state_id;