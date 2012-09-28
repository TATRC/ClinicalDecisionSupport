--Function: public.last_elem(text[])
--Usage:   select last_elem(string_to_array('/model/9', '/'));

--DROP FUNCTION public.last_elem(text[]);

CREATE OR REPLACE FUNCTION last_elem (text[])
RETURNS text AS
'
 SELECT $1[array_length($1,1)];
'
LANGUAGE SQL;
