$files = Get-ChildItem output/gc_*
ForEach ($file in $files){
    $filename = $file.Name
    ./verifier input/$filename output/$filename
}