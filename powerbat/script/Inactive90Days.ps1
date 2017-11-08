$csvpath=$args[0]
Search-ADAccount -AccountInactive -TimeSpan 90.00:00:00 | where {$_.ObjectClass -eq 'user'} | where {$_.Enabled -eq 'true'} | export-csv  $csvpath