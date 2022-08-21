####################################
## NOT MY SOLUTION TO BE REVEIED  ##
####################################

# @link https://www.hackerrank.com/challenges/fractal-trees-all/problem?isFullScreen=true

# Getting iterations count from stdin
read -r iterations

# Initial params (matrix size and initial Y size)
rows=63
cols=100
size=16

# Initial branch position (in the middle of the cols)
line=(cols/2)

# This variable will contain all the lines that will be printed
output=()

# Once generated blank line
blankLine=$(printf "%${cols}s" | tr " " "_")

# Writes the line to the output
writeLine() {
  local out=$blankLine
  for n in "${line[@]}"; do
    out=${out:0:$n-1}1${out:$n}
  done
  output+=("$out")
}

# Forks current branches in line
# Every branch forks to 2 one less and one greater
forkBranches() {
  local result=()
  for n in "${line[@]}"; do
    result+=("$((n - 1))")
    result+=("$((n + 1))")
  done

  line=("${result[@]}")
}

# Widen all branches in current line
widenBranches() {
  local result=()
  for n in "${!line[@]}"; do
    local val="${line[$n]}"
    [[ $n%2 -gt 0 ]] && line[$n]=$val+1 || line[$n]=$val-1
  done
}

# Writing first line
writeLine

# Building the output from the root to the leaves
while ((iterations--)); do
  for ((j = 0; j < size; j++)); do
    writeLine
  done

  forkBranches
  writeLine

  for ((j = 1; j < size; j++)); do
    widenBranches
    writeLine
  done

  size=$size/2
done

# Print the output in reverse order
for ((i = rows; i > 0; i--)); do
  [ "${output[$i]}" ] && echo "${output[$i]}" || echo "$blankLine"
done

