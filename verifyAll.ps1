$files = Get-ChildItem output/ks_*
ForEach ($file in $files){
    $filename = $file.Name
    ./verifier input/$filename output/$filename
}