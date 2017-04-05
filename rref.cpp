#include <iostream>
#include <vector>
using namespace std;

bool inRowReducedEchelonForm(vector<vector<int> >& matrix);
bool allNonZeroRowsAreAboveAnyRowsOfNonZeros(vector<vector<int> >& matrix);
bool contiguousZeroRows(vector<vector<int> >& matrix, int i);
bool eachLeadingEntryOfARowIsInaColumnToTheRightOfTheLeadingEntryOfTheRowAboveIt(vector<vector<int> >& matrix);
int getColOfLeadingEntryIn(vector<int>& vec);
bool allEntriesInAColumnBelowALeadingEntryAreZeros(vector<vector<int> >& matrix);
bool theLeadingEntryInEachNonzeroRowIsOne(vector<vector<int> >& matrix);
int getLeadingEntryFor(vector<int>& vec);
bool isZeroRow(vector<int>& vec);
bool eachLeadingOneIsTheOnlyNonzeroEntryInItsColumn(vector<vector<int> >& matrix);

int main() {
    vector<vector<int> > matrixA;
    
    vector<int> row1;
    row1.push_back(1);
    row1.push_back(0);
    row1.push_back(0);
    
    vector<int> row2;
    row2.push_back(0);
    row2.push_back(1);
    row2.push_back(0);
    
    vector<int> row3;
    row3.push_back(0);
    row3.push_back(0);
    row3.push_back(1);
    
    matrixA.push_back(row1);
    matrixA.push_back(row2);
    matrixA.push_back(row3);
    
    cout << inRowReducedEchelonForm(matrixA);

    return 0;
}

bool inRowReducedEchelonForm(vector<vector<int> >& matrix) {
    if (!allNonZeroRowsAreAboveAnyRowsOfNonZeros(matrix)) return false;
    if (!eachLeadingEntryOfARowIsInaColumnToTheRightOfTheLeadingEntryOfTheRowAboveIt(matrix)) return false;
    if (!allEntriesInAColumnBelowALeadingEntryAreZeros(matrix)) return false;
    if (!theLeadingEntryInEachNonzeroRowIsOne(matrix)) return false;
    if (!eachLeadingOneIsTheOnlyNonzeroEntryInItsColumn(matrix)) return false;
    return true;
}

bool allNonZeroRowsAreAboveAnyRowsOfNonZeros(vector<vector<int> >& matrix) {
    bool zeroRowOnBottom = false;
    for (int i = matrix.size()-1; i >= 0; i--) {
        for (int j = 0; j < matrix[i].size(); j++) {
            if (i == matrix.size()-1) {
                if (isZeroRow(matrix[i])) {
                    zeroRowOnBottom = true;
                }
            } else {
                if (!zeroRowOnBottom) {
                    if (isZeroRow(matrix[i])) return false;
                } else {
                    if (!isZeroRow(matrix[i])) continue;
                    if (!contiguousZeroRows(matrix, i)) return false;
                }
            }
        }
    }
    return true;
}

bool contiguousZeroRows(vector<vector<int> >& matrix, int i) {
    for (int j = matrix.size()-1; j >= i; j--) {
        if (!isZeroRow(matrix[j])) return false;
    }
    return true;
}

bool eachLeadingEntryOfARowIsInaColumnToTheRightOfTheLeadingEntryOfTheRowAboveIt(vector<vector<int> >& matrix) {
    int colOfLeadingEntry = -1;
    for (int i = 0; i < matrix.size(); i++) {
        if (i == 0) {
            colOfLeadingEntry = getColOfLeadingEntryIn(matrix[0]);
        } else {
            if (colOfLeadingEntry >= getColOfLeadingEntryIn(matrix[i])) return false;
            colOfLeadingEntry = getColOfLeadingEntryIn(matrix[i]);
        }
    }
    return true;
}

int getColOfLeadingEntryIn(vector<int>& vec) {
    int result = -1;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] == 0) continue;
        result = i;
        break;
    }
    return result;
}

bool allEntriesInAColumnBelowALeadingEntryAreZeros(vector<vector<int> >& matrix) {
    for (int i = 0; i < matrix.size(); i++) {
        int col = getColOfLeadingEntryIn(matrix[i]);
        for (int j = 0; j < matrix.size(); j++) {
            for (int k = 0; k < matrix[j].size(); k++) {
                if (k != col) continue;
                if (j <= i) continue;
                if (matrix[j][k] != 0) return false;
            }
        }
    }
    return true;
}

bool theLeadingEntryInEachNonzeroRowIsOne(vector<vector<int> >& matrix) {
    for (int i = 0; i < matrix.size(); i++) {
        if (isZeroRow(matrix[i])) continue;
        if (getLeadingEntryFor(matrix[i]) != 1) return false;
    }
    return true;
}

int getLeadingEntryFor(vector<int>& vec) {
    int result = -1;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] == 0) continue;
        result = vec[i];
        break;
    }
    return result;
}

bool isZeroRow(vector<int>& vec) {
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] != 0) return false;
    }
    return true;
}

// pre-condition: presume all leading entries are equal to 1.
bool eachLeadingOneIsTheOnlyNonzeroEntryInItsColumn(vector<vector<int> >& matrix) {
    for (int i = 0; i < matrix.size(); i++) {
        int colOfLeadingOneInColI = getColOfLeadingEntryIn(matrix[i]);
        int numOfNonzeroEntriesInColI = 0;
        for (int j = 0; j < matrix.size(); j++) {
            for (int k = 0; k < matrix[0].size(); k++) {
                if (k != colOfLeadingOneInColI) continue;
                if (matrix[j][k] != 0) numOfNonzeroEntriesInColI++;
            }
        }
        if (numOfNonzeroEntriesInColI != 1) return false;
    }
    return true;
}
