
SELECT * FROM
	information_schema.columns
WHERE
	table_name = '${tableName}'
ORDER BY
	ordinal_position;